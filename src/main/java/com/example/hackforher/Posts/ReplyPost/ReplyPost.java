package com.example.hackforher.Posts.ReplyPost;

import com.example.hackforher.Posts.Post;
import com.example.hackforher.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
public class ReplyPost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type="uuid-char")
    private UUID id;
    private String reply;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"birthDate","phone"})
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Post post;

}
