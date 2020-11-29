package com.example.hackforher.Quotes;

import com.example.hackforher.Quotes.UserFavoriteQuotes.UserFavoriteQuotes;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type="uuid-char")
    private UUID id;
    @NotBlank
    private String description;

    @OneToMany(mappedBy = "quote",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<UserFavoriteQuotes> userFavoriteQuotes=new ArrayList<>();

}
