package com.example.hackforher.Posts.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
public class PostRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    private boolean anonymous;
}
