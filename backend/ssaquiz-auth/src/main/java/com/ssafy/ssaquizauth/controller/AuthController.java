package com.ssafy.ssaquizauth.controller;

import com.ssafy.ssaquizauth.Service.S3Service;
import com.ssafy.ssaquizauth.exception.BadRequestException;
import com.ssafy.ssaquizauth.model.AuthProvider;
import com.ssafy.ssaquizauth.model.BasicResponse;
import com.ssafy.ssaquizauth.model.User;
import com.ssafy.ssaquizauth.payload.*;
import com.ssafy.ssaquizauth.repository.UserRepository;
import com.ssafy.ssaquizauth.security.TokenProvider;

import com.ssafy.ssaquizauth.util.RedisUtil;
import io.swagger.annotations.ApiOperation;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Value("${app.auth.tokenExpirationMsec}")
    private long tokenExpirationMsec;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private S3Service s3Service;

    @Value("${file.defaultImagePath}")
    private String DEFAULT_IMAGE_PATH;

    @ApiOperation(value = "로그인")
    @PostMapping("/login")
    public BasicResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        redisUtil.setDataExpire(loginRequest.getEmail(), "exist", tokenExpirationMsec);

        Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());
        String token = tokenProvider.createToken(authentication);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("accessToken", token);
        jsonObject.put("id", user.get().getId());
        jsonObject.put("nickname", user.get().getName());
        jsonObject.put("email", user.get().getEmail());
        jsonObject.put("imageUrl", user.get().getImageUrl());

        BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "로그인 성공";
        result.object = jsonObject;

        return result;
    }

    @ApiOperation(value = "회원가입")
    @PostMapping("/signup")
    public BasicResponse registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("이메일 중복");
        }

        // Creating user's account
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setProvider(AuthProvider.local);
        user.setImageUrl(DEFAULT_IMAGE_PATH);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/me")
                .buildAndExpand(result.getId()).toUri();

        BasicResponse basicResponse = new BasicResponse();
        basicResponse.status = true;
        basicResponse.data = "회원가입 성공";

        return basicResponse;
    }

    @ApiOperation(value = "로그아웃")
    @PostMapping("/logout")
    public BasicResponse logout(@RequestBody String email) {
        redisUtil.deleteData(email);

        BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "로그아웃 성공";

        return result;
    }

    @ApiOperation(value = "회원정보 수정 (사진포함)")
    @PostMapping("/modify-image")
    public BasicResponse modifyImage(@RequestParam("file") MultipartFile inputFile, @RequestParam("name") String name, @RequestParam("password") String password, @RequestParam("email") String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if(!user.isPresent()) {
            throw new BadRequestException("이메일 없음");
        }

        String imgPath = s3Service.upload(inputFile);

        user.get().setImageUrl(imgPath);
        user.get().setName(name);
        user.get().setPassword(passwordEncoder.encode(password));
        userRepository.save(user.get());

        BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "회원정보 수정 성공";

        return result;
    }

    @ApiOperation(value = "회원정보 수정")
    @PostMapping("/modify")
    public BasicResponse modify(@RequestParam("name") String name, @RequestParam("password") String password, @RequestParam("email") String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if(!user.isPresent()) {
            throw new BadRequestException("이메일 없음");
        }

        user.get().setName(name);
        user.get().setPassword(passwordEncoder.encode(password));
        userRepository.save(user.get());

        BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "회원정보 수정 성공";

        return result;
    }

    @Transactional
    @ApiOperation(value = "회원탈퇴")
    @PostMapping("/withdrawal")
    public BasicResponse delete(@RequestParam("email") String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if(!user.isPresent()) {
            throw new BadRequestException("이메일 없음");
        }

        userRepository.deleteByEmail(email);

        BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "회원 탈퇴 성공";

        return result;
    }
}
