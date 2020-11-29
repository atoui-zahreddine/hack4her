package com.example.hackforher.Posts.Models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ReplyRequest {
    @NotBlank
    private String reply;
}
