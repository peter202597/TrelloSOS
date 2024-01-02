package com.sos.trellosos.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UserRequestDto {

    @Pattern(regexp = "^[a-zA-Z0-9]{4,}$")
    private String username;

    @Pattern(regexp = "^.{4,}$")
    private String password;

    private String checkPassword;

    @Email
    @NotBlank
    private String email;
}
