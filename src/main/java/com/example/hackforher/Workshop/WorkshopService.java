package com.example.hackforher.Workshop;

import com.example.hackforher.User.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class WorkshopService {
    private final WorkshopRepository workshopRepository;

    public WorkshopService(WorkshopRepository workshopRepository) {
        this.workshopRepository = workshopRepository;
    }

    public ResponseEntity<?> createWorkshop(Workshop workshop) {
        workshop.setUser( (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return new ResponseEntity<>(workshopRepository.save(workshop),HttpStatus.CREATED);
    }

    public ResponseEntity<?> getAllWorkshops(){
        return new ResponseEntity<>(workshopRepository.findAll(),HttpStatus.OK);
    }
}
