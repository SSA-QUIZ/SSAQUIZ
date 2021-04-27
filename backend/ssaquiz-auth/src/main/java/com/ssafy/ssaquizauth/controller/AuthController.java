package com.ssafy.ssaquizauth.controller;

import com.ssafy.ssaquizauth.exception.BadRequestException;
import com.ssafy.ssaquizauth.model.AuthProvider;
import com.ssafy.ssaquizauth.model.User;
import com.ssafy.ssaquizauth.payload.*;
import com.ssafy.ssaquizauth.repository.UserRepository;
import com.ssafy.ssaquizauth.security.TokenProvider;

import com.ssafy.ssaquizauth.util.RedisUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Optional;
import java.util.UUID;

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

    @Value("${file.localPath}")
    private String LOCAL_PATH;

    @Value("${file.serverPath}")
    private String SERVER_PATH;

    @Value("${file.imagePath}")
    private String IMAGE_PATH;

    @ApiOperation(value = "로그인")
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        redisUtil.setDataExpire(loginRequest.getEmail(), "exist", tokenExpirationMsec);

        String token = tokenProvider.createToken(authentication);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @ApiOperation(value = "회원가입")
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Email address already in use.");
        }

        // Creating user's account
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setProvider(AuthProvider.local);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/me")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "User registered successfully@"));
    }

    @ApiOperation(value = "로그아웃")
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@PathVariable("email") String email) {
        redisUtil.deleteData(email);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "회원정보수정")
    @PostMapping("/modify")
    public ResponseEntity<?> modify(@RequestParam("file") MultipartFile inputFile, @RequestParam("name") String name, @RequestParam("password") String password, @RequestParam("email") String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if(!user.isPresent()) {
            throw new BadRequestException("Email does not exist.");
        }

        UUID uuid = UUID.randomUUID();
        File file = new File(SERVER_PATH, uuid + ".jpg");

        try {
            inputFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        user.get().setImageUrl(IMAGE_PATH + file.getName());
        user.get().setName(name);
        user.get().setPassword(passwordEncoder.encode(password));
        userRepository.save(user.get());

        return new ResponseEntity(HttpStatus.OK);
    }

}