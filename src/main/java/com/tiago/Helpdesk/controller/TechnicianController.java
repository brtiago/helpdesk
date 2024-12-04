package com.tiago.Helpdesk.controller;

import com.tiago.Helpdesk.domain.Technician;
import com.tiago.Helpdesk.service.TechnicianService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/technicians")
public class TechnicianController {

    private final TechnicianService technicianService;

    public TechnicianController(TechnicianService technicianService){
        this.technicianService = technicianService;
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Technician> findById(@PathVariable Integer id) throws NoSuchMethodException {
        Technician technician = technicianService.findById(id);
        return ResponseEntity.ok().body(technician);
    }

}
