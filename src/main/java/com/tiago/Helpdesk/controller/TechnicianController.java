package com.tiago.Helpdesk.controller;

import com.tiago.Helpdesk.controller.dto.TechnicianDTO;
import com.tiago.Helpdesk.domain.Technician;
import com.tiago.Helpdesk.service.TechnicianService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/technicians")
public class TechnicianController {

    private final TechnicianService technicianService;

    public TechnicianController(TechnicianService technicianService){
        this.technicianService = technicianService;
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<TechnicianDTO> findById(@PathVariable Integer id) {
        Technician technician = technicianService.findById(id);
        TechnicianDTO technicianDTO = new TechnicianDTO(technician)
        return ResponseEntity.ok().body(technicianDTO);
    }

    @GetMapping
    public ResponseEntity<List<TechnicianDTO>> findAll() {
        var technicians = technicianService.findAll();
        var techniciansDTO = technicians.stream().map(TechnicianDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(techniciansDTO);
    }

    @PostMapping
    public ResponseEntity<TechnicianDTO> create() {

        return null;
    }

}
