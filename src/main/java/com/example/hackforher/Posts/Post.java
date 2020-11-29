package com.example.hackforher.Posts;

import com.example.hackforher.Posts.LikePost.LikePost;
import com.example.hackforher.Posts.Models.PostRequest;
import com.example.hackforher.Posts.ReplyPost.ReplyPost;
import com.example.hackforher.User.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.*;

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
    @JsonIgnoreProperties({"birthDate","phone"})
    private User user;

    @ManyToMany(mappedBy = "favoritePosts",cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<User> usersFavoritePosts=new HashSet<>();

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<LikePost> usersLikes=new HashSet<>();

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private Set<ReplyPost> postReplys=new HashSet<>();

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
    public int getNumberOfLikes(){
        return usersLikes.size();
    }
}
