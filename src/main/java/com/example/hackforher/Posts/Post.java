package com.example.hackforher.Posts;

import com.example.hackforher.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
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
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

}
