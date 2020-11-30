package com.example.hackforher.Posts.DTO;

import com.example.hackforher.Posts.ReplyPost.ReplyPost;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class UserLikedPostResponse {
    private UUID id;
    private String title;
    private String description;
    private boolean isAnonymous;
    private int numberOfLikes;
    private Set<ReplyPost> postReplys;

    public UserLikedPostResponse(UUID id, String title, String description, boolean isAnonymous, int numberOfLikes, Set<ReplyPost> postReplys) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isAnonymous = isAnonymous;
        this.numberOfLikes = numberOfLikes;
        this.postReplys = postReplys;
    }
}
