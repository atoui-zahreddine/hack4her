package com.example.hackforher.Jobs;

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
public class Job {
    @Id
    private UUID Id;
    private String description;
    private String title;
    private String location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

}
