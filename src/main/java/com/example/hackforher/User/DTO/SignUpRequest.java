package com.example.hackforher.User.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequest {
    @NotBlank(message = "phone field is undefined or blank ")
    private String phone;
    @NotBlank(message="password field is undefined or blank")
    @Size(min = 8,message = "please add a password with 8+ characters")
    private String password;
    @NotBlank(message = "username is undefined")
    private String username;
}
