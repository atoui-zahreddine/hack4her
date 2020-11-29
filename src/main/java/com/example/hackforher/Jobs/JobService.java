package com.example.hackforher.Jobs;

import com.example.hackforher.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class JobService {
    private final JobRepository jobRepository;

    @Autowired
    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }


    public ResponseEntity<?> createJob(Job request, User user) {
        request.setUser(user);
        return new ResponseEntity<>(jobRepository.save(request), HttpStatus.CREATED);
    }

    public ResponseEntity<?> getAllJobs() {
        return new ResponseEntity<>(jobRepository.findAll(),HttpStatus.OK);
    }

}
