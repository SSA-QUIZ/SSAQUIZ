package com.ssafy.ssaquizauth.controller;

import com.ssafy.ssaquizauth.dto.UserDto;
import com.ssafy.ssaquizauth.service.UserService;
import com.ssafy.ssaquizauth.model.BasicResponse;
import com.ssafy.ssaquizauth.payload.*;

import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    UserService userService;

    @ApiOperation(value = "로그인")
    @PostMapping("/login")
    public BasicResponse authenticateUser(@RequestBody LoginRequest loginRequest) {
        return userService.authenticateUser(loginRequest);
    }

    @ApiOperation(value = "회원가입")
    @PostMapping("/signup")
    public BasicResponse registerUser(@RequestBody SignUpRequest signUpRequest) {
        return userService.registerUser(signUpRequest);
    }

    @ApiOperation(value = "로그아웃")
    @PostMapping("/logout")
    public BasicResponse logout(@RequestBody String email) {
        return userService.logout(email);
    }

    @ApiOperation(value = "회원정보 수정 (사진포함)")
    @PutMapping("/user-image")
    public BasicResponse modifyImage(@RequestParam("file") MultipartFile inputFile, @RequestParam("name") String name, @RequestParam("password") String password, @RequestParam("email") String email) {
        return userService.modifyImage(inputFile, name, password, email);
    }

    @ApiOperation(value = "회원정보 수정")
    @PutMapping("/user")
    public BasicResponse modify(@RequestBody UserDto userDto) {
        return userService.modify(userDto);
    }

    @ApiOperation(value = "회원탈퇴")
    @DeleteMapping("/user/{email}")
    public BasicResponse delete(@PathVariable("email") String email) {
        return userService.delete(email);
    }

    @ApiOperation(value = "회원정보 조회")
    @PostMapping("/user")
    public BasicResponse find(@RequestBody String token) {
        return userService.find(token);
    }
}
