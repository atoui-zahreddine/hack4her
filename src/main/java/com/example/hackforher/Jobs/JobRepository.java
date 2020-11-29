package com.example.hackforher.Jobs;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JobRepository extends JpaRepository<Job, UUID> {
    @EntityGraph(attributePaths = {"user"})
    List<Job> findAll();

    @EntityGraph(attributePaths = {"user"})
    List<Job> findTop5ByOrderByCreationDateDesc();
}
