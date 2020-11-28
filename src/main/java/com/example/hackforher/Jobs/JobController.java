package com.example.hackforher.Jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping({"","/"})
    public ResponseEntity<?> createJob(@Valid @RequestBody Job request){
        return jobService.createJob(request);
    }
    @GetMapping("")
    public ResponseEntity<?> getAllJobs(){
        return jobService.getAllJobs();
    }
}
