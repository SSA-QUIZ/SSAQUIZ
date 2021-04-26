package com.ssafy.ssaquizauth.controller;

import com.ssafy.ssaquizauth.exception.ResourceNotFoundException;
import com.ssafy.ssaquizauth.model.User;
import com.ssafy.ssaquizauth.repository.UserRepository;
import com.ssafy.ssaquizauth.security.CurrentUser;
import com.ssafy.ssaquizauth.security.UserPrincipal;

import springfox.documentation.annotations.ApiIgnore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@ApiIgnore
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}
