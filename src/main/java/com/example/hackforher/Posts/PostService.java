package com.example.hackforher.Posts;

import com.example.hackforher.Exception.NotFoundException;
import com.example.hackforher.Posts.Models.PostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public ResponseEntity<?> createPost(PostRequest request) {
        var newPost=new Post(request);
        postRepository.save(newPost);
        return new ResponseEntity<>(postRepository.save(newPost), HttpStatus.OK);
    }

    public ResponseEntity<?> getAllPosts(){
        return new ResponseEntity(postRepository.getAllPostsSorted(),HttpStatus.OK);
    }
    public ResponseEntity<?> getPostById(UUID postId){
        var post=postRepository.findById(postId)
                .orElseThrow(()-> new NotFoundException(HttpStatus.NOT_FOUND.value(), "no post with this id ."));
        return new ResponseEntity<>(post,HttpStatus.OK);
    }

    public ResponseEntity<?> removePostById(UUID postId,UUID userId){
        if(postRepository.checkIfPostExist(postId,userId) == 0 )
                throw  new NotFoundException(HttpStatus.NOT_FOUND.value(), "no post with this id .");
        postRepository.deleteById(postId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
