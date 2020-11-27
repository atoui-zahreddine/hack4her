package com.example.hackforher.Security;

import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class PublicURI {
    private  static final RequestMatcher PUBLIC_URI= new OrRequestMatcher(
            new AntPathRequestMatcher("/**", HttpMethod.OPTIONS.name()),
            new AntPathRequestMatcher("/auth/**")
    );

    public static RequestMatcher getPublicUri() {
        return PUBLIC_URI;
    }
}
