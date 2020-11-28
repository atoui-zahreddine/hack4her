package com.example.hackforher.Posts.LikePost;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.UUID;

@Repository
public interface LikePostRepository extends JpaRepository<LikePost, UUID> {

    @Query("select l from LikePost l where l.post.id=:postId ")
    @EntityGraph(attributePaths = {"user","post"})
    List<LikePost> getPostLikes(@PathParam("postId") UUID postId);

    @Query("select count(l) as COL_0_0 from LikePost l where l.user.id=:userId and l.post.id=:postId")
    int isAlreadyLiked(@Param("postId") UUID postId , @Param("userId") UUID userId);
}
