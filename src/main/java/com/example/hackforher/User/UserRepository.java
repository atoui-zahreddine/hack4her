package com.example.hackforher.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository()
public interface UserRepository  extends JpaRepository<User, UUID> {
    @Query("select u from User u  where u.email=:email")
    Optional<User> getUserByEmail(@Param("email") String email);
}
