package com.example.hackforher.Jobs;

import com.example.hackforher.User.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
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
    private int salary;
    @NotBlank
    private String contact;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate=new Date();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"birthDate","phone"})
    private User user;

}
