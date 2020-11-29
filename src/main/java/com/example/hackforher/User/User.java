package com.example.hackforher.User;

import com.example.hackforher.Jobs.Job;
import com.example.hackforher.Posts.LikePost.LikePost;
import com.example.hackforher.Posts.Post;
import com.example.hackforher.Quotes.UserFavoriteQuotes.UserFavoriteQuotes;
import com.example.hackforher.User.Models.SignUpRequest;
import com.example.hackforher.Workshop.Workshop;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
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
    private String username;
    private String name;
    private String lastName;
    private String avatar;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthDate;
    private String phone;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Post> posts=new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Job> jobs=new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Workshop> workshops=new ArrayList<>();

    @ManyToMany(cascade= {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(
            name = "user_favorite_posts",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id")
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Set<Post> favoritePosts=new HashSet<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<UserFavoriteQuotes> favoriteQuotes=new HashSet<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<LikePost> userLikedPosts=new ArrayList<>();

    public User(SignUpRequest signUpRequest) {
        this.password=signUpRequest.getPassword();
        this.username=signUpRequest.getUsername();
        this.phone=signUpRequest.getPhone();
        this.avatar=getAvatar();
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getName() {
        return name!=null?name:"";
    }

    public String getLastName() {
        return lastName!=null?name:"";
    }

    public Date getBirthDate() {
        return birthDate;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    public String getAvatar() {
        return (name!=null && lastName!=null) ?
                "https://avatar.oxro.io/avatar.svg?name="+name.toUpperCase()+"+"+lastName.toUpperCase()+"&background=6ab04c&color=000"
                :"https://avatar.oxro.io/avatar.svg?name="+username.toUpperCase()+"&background=6ab04c&color=000";
    }

    public void merge(User user) {
        name=user.name;
        lastName=user.lastName;
        avatar=getAvatar();
        birthDate=user.birthDate;
        phone=user.phone;
        password=user.password;
    }
}
