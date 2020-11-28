package com.example.hackforher.User;

import com.example.hackforher.User.Models.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping({"/me","/me/"})
    public ResponseEntity<?> updateUser(@RequestBody SignUpRequest request, @AuthenticationPrincipal User user){
        return userService.updateUser(request,user);
    }
}
