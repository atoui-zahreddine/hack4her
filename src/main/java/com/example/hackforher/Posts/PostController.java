package com.example.hackforher.Posts;

import com.example.hackforher.Posts.DTO.PostRequest;
import com.example.hackforher.Posts.DTO.ReplyRequest;
import com.example.hackforher.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping({"/",""})
    public ResponseEntity<?> createPost(@Valid @RequestBody PostRequest request,@AuthenticationPrincipal User user){
        return postService.createPost(request,user);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllPosts(){
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPostById(@PathVariable String id){
        return postService.getPostById(UUID.fromString(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removePostById(@PathVariable String id, @AuthenticationPrincipal User user){
        return postService.removePostById(UUID.fromString(id),user.getId());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable String id,@Valid @RequestBody PostRequest request,@AuthenticationPrincipal User user){
        return postService.updatePostById(UUID.fromString(id),user.getId(),request);
    }

    @PostMapping("/favorites/{id}")
    public ResponseEntity<?> addFavoritePost(@PathVariable String id,@AuthenticationPrincipal User user){
        return postService.addFavoritePost(UUID.fromString(id),user);
    }

    @GetMapping("/favorites")
    public ResponseEntity<?> getFavoritePosts(@AuthenticationPrincipal User user){
        return postService.getFavoritePosts(user);
    }

    @PostMapping("/likes/{id}")
    public ResponseEntity<?> likePost(@PathVariable String id,@AuthenticationPrincipal User user){
        return postService.likePost(id,user);
    }

    @GetMapping("/likes/{id}")
    public ResponseEntity<?> getPostLikes(@PathVariable String id){
        return postService.getPostLikes(id);
    }

    @PostMapping("/reply/{id}")
    public ResponseEntity<?> replyPost(@PathVariable String id,
                                       @AuthenticationPrincipal User user,
                                       @Valid @RequestBody ReplyRequest replyRequest
                                       )
    { return postService.addReply(UUID.fromString(id),replyRequest,user);
    }


}
