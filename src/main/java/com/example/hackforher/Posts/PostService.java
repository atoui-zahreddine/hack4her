package com.example.hackforher.Posts;

import com.example.hackforher.Posts.Models.PostRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    public ResponseEntity<?> createPost(PostRequest request) {
        return new ResponseEntity<>(request, HttpStatus.OK);
    }
}
