package com.example.hackforher.Quotes.UserFavoriteQuotes;

import com.example.hackforher.Quotes.Quote;
import com.example.hackforher.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@NamedEntityGraph(name = "quote",
        attributeNodes = @NamedAttributeNode("quote")
)
public class UserFavoriteQuotes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type="uuid-char")
    @JsonIgnore
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Quote quote;
}
