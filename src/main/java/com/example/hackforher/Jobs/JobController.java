package com.example.hackforher.Jobs;

import com.example.hackforher.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<?> createJob(@Valid @RequestBody Job request,@AuthenticationPrincipal User user){
        return jobService.createJob(request,user);
    }
    @GetMapping("")
    public ResponseEntity<?> getAllJobs(){
        return jobService.getAllJobs();
    }

    @GetMapping("/latest")
    public ResponseEntity<?> getLatestJobs(){
        return jobService.getLatestJobs();
    }
}
