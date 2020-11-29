package com.example.hackforher.Posts;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository()
public interface PostRepository extends JpaRepository<Post, UUID> {


    @EntityGraph(attributePaths = {"user","postReplys","usersLikes"})
    @Query("select p from Post p order by p.date desc ")
    List<Post> getAllPostsSorted();

    @EntityGraph(attributePaths = {"usersFavoritePosts","user","postReplys","usersLikes"})
    Optional<Post> findById(UUID postId);

    @Query("select count (p.id) from Post p where p.user.id=:userId and p.id=:postId")
    int checkIfPostExist(@Param("postId") UUID postId,@Param("userId") UUID userId);


    @Query("select p from Post p where p.user.id=:userId and p.id=:postId")
    @EntityGraph(attributePaths = {"user","postReplys"})
    Optional<Post> getUserPostById(@Param("postId") UUID postId,@Param("userId") UUID userId);

    @Query(nativeQuery = true,value = "Insert into user_favorite_posts values (:userId,:postId)")
    @Transactional
    @Modifying
    void addFavoritePost(@Param("userId") String userId, @Param("postId") String postId);
}
