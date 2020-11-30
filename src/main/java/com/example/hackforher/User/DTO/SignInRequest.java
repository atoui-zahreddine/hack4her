package com.example.hackforher.User.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SignInRequest {
    @NotBlank(message = "username field is undefined or blank ")
    private String username;
    @Size(min = 8,message = "please add a password with 8+ characters")
    @NotBlank(message="password field is undefined or blank")
    private String password;
}
