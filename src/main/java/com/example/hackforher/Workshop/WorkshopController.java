package com.example.hackforher.Workshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/workshops")
public class WorkshopController {
    private final WorkshopService workshopService;

    @Autowired()
    public WorkshopController( WorkshopService workshopService) {
        this.workshopService = workshopService;
    }

    @PostMapping("")
    public ResponseEntity<?> createWorkshop(@Valid @RequestBody Workshop workshop){
        return workshopService.createWorkshop(workshop);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllWorkshops(){
        return workshopService.getAllWorkshops();
    }


}
