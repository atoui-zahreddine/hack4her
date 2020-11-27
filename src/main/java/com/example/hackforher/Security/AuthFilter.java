package com.example.hackforher.Security;


import com.example.hackforher.Exception.AuthorizationException;
import com.example.hackforher.Exception.NotFoundException;
import com.example.hackforher.User.User;
import com.example.hackforher.User.UserService;
import com.example.hackforher.Utils.JwtUtils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthFilter extends OncePerRequestFilter {
    @Value("${auth.header}")
    private String HEADER;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserService userService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException , AuthorizationException, NotFoundException {
        var header=request.getHeader(HEADER);
        String HEADER_PREFIX = "Bearer ";
        if(header != null && header.startsWith(HEADER_PREFIX) && SecurityContextHolder.getContext()!=null){
            var token = header.replace(HEADER_PREFIX,"");
            var username=jwtUtils.getUsernameFromToken(token);
            if(username!=null){
                var user=(User) userService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(
                        user,null,user.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }else {
                throw new AuthorizationException("bad token",240);
            }
        }
        filterChain.doFilter(request,response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return PublicURI.getPublicUri().matches(request);
    }

}
