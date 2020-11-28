package com.example.hackforher.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping({"/me","/me/"})
    public ResponseEntity<?> updateUser(@RequestBody User request, @AuthenticationPrincipal User user){
        return userService.updateUser(request,user);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id){
        return userService.getUserByID(UUID.fromString(id));
    }
}
