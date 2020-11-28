package com.example.hackforher.Posts;

import com.example.hackforher.Exception.NotFoundException;
import com.example.hackforher.Posts.Models.PostRequest;
import com.example.hackforher.User.User;
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
        Post post = getPost(postId);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }

    private Post getPost(UUID postId) {
        var post=postRepository.findById(postId)
                .orElseThrow(()-> new NotFoundException(HttpStatus.NOT_FOUND.value(), "no post with this id ."));
        return post;
    }

    public ResponseEntity<?> removePostById(UUID postId,UUID userId){
        if(postRepository.checkIfPostExist(postId,userId) == 0 )
                throw  new NotFoundException(HttpStatus.NOT_FOUND.value(), "no post with this id .");
        postRepository.deleteById(postId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> updatePostById(UUID postId, UUID userId,PostRequest request){
        var post =postRepository.getUserPostById(postId,userId).
                orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "you have no post with this id"));
        post.merge(request);
        postRepository.save(post);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }

    public ResponseEntity<?> addFavoritePost(UUID postId, User user) {
        var post =postRepository.findById(postId).
                orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), " no post with this id"));
        post.getUsersFavoritePosts().add(user);
        postRepository.save(post);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
