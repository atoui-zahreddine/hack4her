package com.example.hackforher.Jobs;

import com.example.hackforher.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type="uuid-char")
    private UUID Id;
    @NotBlank
    private String description;
    @NotBlank
    private String title;
    @NotBlank
    private String location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

}
