package com.ssafy.ssaquizauth.Service;

import com.ssafy.ssaquizauth.model.AuthProvider;
import com.ssafy.ssaquizauth.model.BasicResponse;
import com.ssafy.ssaquizauth.model.User;
import com.ssafy.ssaquizauth.payload.LoginRequest;
import com.ssafy.ssaquizauth.payload.SignUpRequest;
import com.ssafy.ssaquizauth.repository.UserRepository;
import com.ssafy.ssaquizauth.security.TokenProvider;
import com.ssafy.ssaquizauth.util.RedisUtil;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private RedisUtil redisUtil;

    @Value("${app.auth.tokenExpirationMsec}")
    private long tokenExpirationMsec;

    @Value("${file.defaultImagePath}")
    private String DEFAULT_IMAGE_PATH;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private S3Service s3Service;

    public BasicResponse authenticateUser(LoginRequest loginRequest) {
        BasicResponse result = new BasicResponse();

        if (loginRequest == null) {
            result.status = false;
            result.data = "로그인 실패(null)";
            return result;
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());
        String token = tokenProvider.createToken(authentication);
        redisUtil.setDataExpire(loginRequest.getEmail(), "exist", tokenExpirationMsec);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("accessToken", token);
        jsonObject.put("id", user.get().getId());
        jsonObject.put("nickname", user.get().getName());
        jsonObject.put("email", user.get().getEmail());
        jsonObject.put("imageUrl", user.get().getImageUrl());

        result.status = true;
        result.data = "로그인 성공";
        result.object = jsonObject;
        return result;
    }

    public BasicResponse registerUser(SignUpRequest signUpRequest) {
        BasicResponse result = new BasicResponse();

        if (signUpRequest == null) {
            result.status = false;
            result.data = "회원가입 실패(null)";
            return result;
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            result.status = false;
            result.data = "회원가입 실패(이메일 중복)";
            return result;
        }

        User user = new User();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setProvider(AuthProvider.local);
        user.setImageUrl(DEFAULT_IMAGE_PATH);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User saveUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/me")
                .buildAndExpand(saveUser.getId()).toUri();

        result.status = true;
        result.data = "회원가입 성공";
        return result;
    }

    public BasicResponse logout(String email) {
        BasicResponse result = new BasicResponse();

        if(email == null) {
            result.status = false;
            result.data = "로그아웃 실패(null)";
            return result;
        }

        if("".equals(email)) {
            result.status = false;
            result.data = "로그아웃 실패(null string)";
            return result;
        }

        if(redisUtil.getData(email) == null) {
            result.status = false;
            result.data = "로그아웃 실패(존재하는 이메일 없음)";
            return result;
        }

        redisUtil.deleteData(email);

        result.status = true;
        result.data = "로그아웃 성공";
        return result;
    }

    public BasicResponse modifyImage(MultipartFile inputFile, String name, String password, String email) {
        BasicResponse result = new BasicResponse();

        if (inputFile == null || email == null) {
            result.status = false;
            result.data = "회원정보 수정 실패(null)";
            return result;
        }

        Optional<User> user = userRepository.findByEmail(email);
        if(!user.isPresent()) {
            result.status = false;
            result.data = "회원정보 수정 실패(존재하는 이메일 없음)";
            return result;
        }

        String imgPath = s3Service.upload(inputFile);
        user.get().setImageUrl(imgPath);

        if(name != null && !"".equals(name)){
            user.get().setName(name);
        }

        if(password != null && !"".equals(password)) {
            user.get().setPassword(passwordEncoder.encode(password));
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("imageUrl", imgPath);

        userRepository.save(user.get());
        result.status = true;
        result.data = "회원정보 수정 성공";
        result.object = jsonObject;
        return result;
    }

    public BasicResponse modify(String name, String password, String email) {
        BasicResponse result = new BasicResponse();

        if (email == null) {
            result.status = false;
            result.data = "회원정보 수정 실패(null)";
            return result;
        }

        Optional<User> user = userRepository.findByEmail(email);
        if(!user.isPresent()) {
            result.status = false;
            result.data = "회원정보 수정 실패(존재하는 이메일 없음)";
            return result;
        }

        if(name != null && !"".equals(name)){
            user.get().setName(name);
        }

        if(password != null && !"".equals(password)) {
            user.get().setPassword(passwordEncoder.encode(password));
        }

        userRepository.save(user.get());
        result.status = true;
        result.data = "회원정보 수정 성공";
        return result;
    }

    @Transactional
    public BasicResponse delete(String email) {
        BasicResponse result = new BasicResponse();

        if (email == null) {
            result.status = false;
            result.data = "회원탈퇴 실패(null)";
            return result;
        }

        Optional<User> user = userRepository.findByEmail(email);
        if(!user.isPresent()) {
            result.status = false;
            result.data = "회원탈퇴 실패(존재하는 이메일 없음)";
            return result;
        }

        userRepository.deleteByEmail(email);

        result.status = true;
        result.data = "회원 탈퇴 성공";
        return result;
    }
}
