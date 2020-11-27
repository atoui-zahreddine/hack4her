package com.example.hackforher.Posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository()
public interface PostRepository extends JpaRepository<Post, UUID> {
    @Query("select p from Post p order by p.date desc ")
    List<Post> getAllPostsSorted();

    @Query("select count (p.id) from Post p where p.user.id=:userId and p.id=:postId")
    int checkIfPostExist(@Param("postId") UUID postId,@Param("userId") UUID userId);
}
