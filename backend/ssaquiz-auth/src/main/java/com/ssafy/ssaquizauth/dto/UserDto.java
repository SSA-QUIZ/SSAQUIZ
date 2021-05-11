package com.ssafy.ssaquizauth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
public class UserDto {
    private String name;
    private String password;
    private String email;
}
