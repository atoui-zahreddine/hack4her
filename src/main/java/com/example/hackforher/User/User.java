package com.example.hackforher.User;

import com.example.hackforher.Jobs.Job;
import com.example.hackforher.Posts.Post;
import com.example.hackforher.User.Models.SignUpRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class User  implements UserDetails {

    @Id
    private UUID Id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private Date birthDate;
    private String phone;


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Post> posts=new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Job> jobs=new ArrayList<>();

    public User(SignUpRequest signUpRequest) {
        this.email=signUpRequest.getEmail();
        this.name=signUpRequest.getName();
        this.lastName=signUpRequest.getLastName();
        this.phone=signUpRequest.getPhoneNumber();
        this.password=signUpRequest.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
