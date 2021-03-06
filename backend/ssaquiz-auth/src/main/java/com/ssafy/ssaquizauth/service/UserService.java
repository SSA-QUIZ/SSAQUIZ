package com.ssafy.ssaquizauth.service;

import com.ssafy.ssaquizauth.dto.UserDto;
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

    @Value("${app.auth.tokenExpirationSec}")
    private long tokenExpirationSec;

    @Value("${file.defaultImagePath}")
    private String DEFAULT_IMAGE_PATH;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private S3Service s3Service;

    public BasicResponse authenticateUser(LoginRequest loginRequest) {
        BasicResponse result = new BasicResponse();
        result.status = false;

        if (loginRequest == null || loginRequest.getEmail() == null || loginRequest.getPassword() == null || "".equals(loginRequest.getEmail()) || "".equals(loginRequest.getPassword())) {
            result.data = "login Fail (loginRequest is null)";
            return result;
        }

        Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());
        if (!user.isPresent()) {
            result.data = "login Fail (email does not exist)";
            return result;
        }

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.get().getPassword())) {
            result.data = "login Fail (password mismatch)";
            return result;
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.createToken(authentication);
        redisUtil.setDataExpire(loginRequest.getEmail(), "exist", tokenExpirationSec);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("accessToken", token);
        jsonObject.put("id", user.get().getId());
        jsonObject.put("nickname", user.get().getName());
        jsonObject.put("email", user.get().getEmail());
        jsonObject.put("imageUrl", user.get().getImageUrl());

        result.status = true;
        result.data = "login success";
        result.object = jsonObject;
        return result;
    }

    public BasicResponse registerUser(SignUpRequest signUpRequest) {
        BasicResponse result = new BasicResponse();

        if (signUpRequest == null || signUpRequest.getEmail() == null || signUpRequest.getName() == null || signUpRequest.getPassword() == null) {
            result.status = false;
            result.data = "signup fail (null)";
            return result;
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            result.status = false;
            result.data = "signup fail (duplicate email)";
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
        result.data = "signup success";
        return result;
    }

    public BasicResponse logout(String email) {
        BasicResponse result = new BasicResponse();

        if (email == null) {
            result.status = false;
            result.data = "logout fail (email is null)";
            return result;
        }

        if ("".equals(email)) {
            result.status = false;
            result.data = "logout fail (email is null string)";
            return result;
        }

        if (redisUtil.getData(email) == null) {
            result.status = false;
            result.data = "logout fail (email does not exist)";
            return result;
        }

        redisUtil.deleteData(email);

        result.status = true;
        result.data = "logout success";
        return result;
    }

    public BasicResponse modifyImage(MultipartFile inputFile, String name, String password, String email) {
        BasicResponse result = new BasicResponse();

        if (inputFile == null || email == null) {
            result.status = false;
            result.data = "modify fail (null)";
            return result;
        }

        Optional<User> user = userRepository.findByEmail(email);
        if (!user.isPresent()) {
            result.status = false;
            result.data = "modify fail (email does not exist)";
            return result;
        }

        String imgPath = s3Service.upload(inputFile);
        user.get().setImageUrl(imgPath);

        if (name != null && !"".equals(name)) {
            user.get().setName(name);
        }

        if (password != null && !"".equals(password)) {
            user.get().setPassword(passwordEncoder.encode(password));
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("imageUrl", imgPath);

        userRepository.save(user.get());
        result.status = true;
        result.data = "modify success";
        result.object = jsonObject;
        return result;
    }

    public BasicResponse modify(UserDto userDto) {
        BasicResponse result = new BasicResponse();

        if (userDto == null || userDto.getEmail() == null) {
            result.status = false;
            result.data = "modify fail (null)";
            return result;
        }

        Optional<User> user = userRepository.findByEmail(userDto.getEmail());
        if (!user.isPresent()) {
            result.status = false;
            result.data = "modify fail (email does not exist)";
            return result;
        }

        String name = userDto.getName();
        if (name != null && !"".equals(name)) {
            user.get().setName(name);
        }

        String password = userDto.getPassword();
        if (password != null && !"".equals(password)) {
            user.get().setPassword(passwordEncoder.encode(password));
        }

        userRepository.save(user.get());
        result.status = true;
        result.data = "modify success";
        return result;
    }

    @Transactional
    public BasicResponse delete(String email) {
        BasicResponse result = new BasicResponse();

        if (email == null) {
            result.status = false;
            result.data = "withdrawal fail (null)";
            return result;
        }

        Optional<User> user = userRepository.findByEmail(email);
        if (!user.isPresent()) {
            result.status = false;
            result.data = "withdrawal fail (email does not exist)";
            return result;
        }

        userRepository.deleteByEmail(email);

        result.status = true;
        result.data = "withdrawal success";
        return result;
    }

    public BasicResponse find(String token) {
        BasicResponse result = new BasicResponse();
        result.status = false;
        result.data = "find fail";

        if (token == null) {
            result.data = "find fail (token is null)";
            return result;
        }

        String email = redisUtil.getData(token);
        if (email == null) {
            result.data = "find fail (email does not exist)";
            return result;
        }

        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("accessToken", token);
            jsonObject.put("id", user.get().getId());
            jsonObject.put("nickname", user.get().getName());
            jsonObject.put("email", user.get().getEmail());
            jsonObject.put("imageUrl", user.get().getImageUrl());

            result.status = true;
            result.data = "find suceess";
            result.object = jsonObject;
            return result;
        }

        return result;
    }
}
