package com.example.hackforher.Auth;

import com.example.hackforher.User.DTO.SignInRequest;
import com.example.hackforher.User.DTO.SignUpRequest;
import com.example.hackforher.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping({"/sign-in","/sing-in/"})
    public ResponseEntity<?> login(@Valid @RequestBody SignInRequest request){
        return userService.login(request);
    }

    @PostMapping({"/sign-up","/sign-up/"})
    public ResponseEntity<?> createUser(@Valid @RequestBody SignUpRequest request){
        return userService.createUser(request);
    }


}
