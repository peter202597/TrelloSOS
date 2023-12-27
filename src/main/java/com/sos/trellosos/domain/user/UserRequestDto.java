package com.sos.trellosos.domain.user;

import lombok.Getter;

@Getter
public class UserRequestDto {

    private String username;

    private String password;

    private String checkPassword;

    private String email;
}
