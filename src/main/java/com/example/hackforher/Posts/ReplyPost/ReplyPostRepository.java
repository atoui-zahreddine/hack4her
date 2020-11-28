package com.example.hackforher.Posts.ReplyPost;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReplyPostRepository extends JpaRepository<ReplyPost, UUID> {
}
