package com.example.hackforher.Posts;

import com.example.hackforher.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Post {
    @Id
    private UUID id;
    private String title;
    private String description;
    private boolean isAnonymous;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

}
