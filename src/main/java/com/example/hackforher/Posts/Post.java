package com.example.hackforher.Posts;

import com.example.hackforher.Posts.Models.PostRequest;
import com.example.hackforher.User.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type="uuid-char")
    private UUID id;
    private String title;
    private String description;
    private boolean isAnonymous;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    @ManyToMany(mappedBy = "favoritePosts",cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonIgnore
    private Set<User> usersFavoritePosts=new HashSet<>();

    public Post(PostRequest request) {
        title=request.getTitle();
        description=request.getDescription();
        isAnonymous=request.isAnonymous();
        date=new Date();
    }
    public void merge(PostRequest request){
            title=request.getTitle();
            description=request.getDescription();
            isAnonymous=request.isAnonymous();
    }
}
