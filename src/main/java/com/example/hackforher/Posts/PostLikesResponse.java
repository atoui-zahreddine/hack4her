package com.example.hackforher.Posts;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PostLikesResponse {

    private UUID id;
    private String name;
    private String lastName;
    private String username;

    public PostLikesResponse(UUID id, String name, String lastName, String username) {
        this.id = id;
        this.name = name!=null?name:"";
        this.lastName = lastName!=null?lastName:"";
        this.username = username;
    }
}
