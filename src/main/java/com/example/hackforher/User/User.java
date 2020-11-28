package com.example.hackforher.User;

import com.example.hackforher.Jobs.Job;
import com.example.hackforher.Posts.Post;
import com.example.hackforher.User.Models.SignUpRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class User  implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type="uuid-char")
    private UUID id;
    private String name;
    private String lastName;
    private String email;
    private String avatar;
    @JsonIgnore
    private String password;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthDate;
    private String phone;


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Post> posts=new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Job> jobs=new ArrayList<>();

    public User(SignUpRequest signUpRequest) {
        this.email=signUpRequest.getEmail();
        this.name=signUpRequest.getName();
        this.lastName=signUpRequest.getLastName();
        this.phone=signUpRequest.getPhone();
        this.password=signUpRequest.getPassword();
        this.avatar="https://avatar.oxro.io/avatar.svg?name="+name.toUpperCase()+"+"+lastName.toUpperCase()+"&background=6ab04c&color=000";
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return email;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

    public void merge(SignUpRequest newUser) {
        this.email=newUser.getEmail();
        this.name=newUser.getName();
        this.lastName=newUser.getLastName();
        this.phone=newUser.getPhone();
        this.password=newUser.getPassword();
    }
}
