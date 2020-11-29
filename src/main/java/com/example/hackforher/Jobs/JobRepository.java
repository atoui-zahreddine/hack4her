package com.example.hackforher.Jobs;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JobRepository extends JpaRepository<Job, UUID> {
    @EntityGraph(attributePaths = {"user"})
    List<Job> findAll();

    @Query(value = "select * from job j order by j.creation_date desc limit 5",nativeQuery = true)
    @EntityGraph(attributePaths = {"user"})
    List<Job> getLatestJobs();
}
