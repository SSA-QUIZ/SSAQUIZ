package com.ssafy.ssaquizauth.controller;

import com.ssafy.ssaquizauth.Service.UserService;
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
    public BasicResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return userService.authenticateUser(loginRequest);
    }

    @ApiOperation(value = "회원가입")
    @PostMapping("/signup")
    public BasicResponse registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        return userService.registerUser(signUpRequest);
    }

    @ApiOperation(value = "로그아웃")
    @PostMapping("/logout")
    public BasicResponse logout(@RequestBody String email) {
        return userService.logout(email);
    }

    @ApiOperation(value = "회원정보 수정 (사진포함)")
    @PostMapping("/modify-image")
    public BasicResponse modifyImage(@RequestParam("file") MultipartFile inputFile, @RequestParam("name") String name, @RequestParam("password") String password, @RequestParam("email") String email) {
        return userService.modifyImage(inputFile, name, password, email);
    }

    @ApiOperation(value = "회원정보 수정")
    @PostMapping("/modify")
    public BasicResponse modify(@RequestParam("name") String name, @RequestParam("password") String password, @RequestParam("email") String email) {
        return userService.modify(name, password, email);
    }

    @ApiOperation(value = "회원탈퇴")
    @PostMapping("/withdrawal")
    public BasicResponse delete(@RequestParam("email") String email) {
        return userService.delete(email);
    }
}
