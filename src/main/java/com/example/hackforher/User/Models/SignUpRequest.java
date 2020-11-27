package com.example.hackforher.User.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequest {
    @Email(message = "invalid email address")
    @NotBlank(message = "email field is undefined or blank ")
    private String email;
    @NotBlank(message="password field is undefined or blank")
    @Size(min = 8,message = "please add a password with 8+ characters")
    private String password;
    @NotBlank(message = "name is undefined")
    private String name;
    @NotBlank(message = "lastName is undefined")
    private String lastName;
    @NotBlank(message = "phone is undefined")
    @Size(min=8,message = "phone should have 8 characters")
    private String phone;
}
