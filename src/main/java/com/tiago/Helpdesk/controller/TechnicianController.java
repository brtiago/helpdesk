package com.tiago.Helpdesk.controller;

import com.tiago.Helpdesk.controller.dto.TechnicianDTO;
import com.tiago.Helpdesk.domain.Technician;
import com.tiago.Helpdesk.service.TechnicianService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

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
        TechnicianDTO technicianDTO = new TechnicianDTO(technician);
        return ResponseEntity.ok().body(technicianDTO);
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<TechnicianDTO>> findAll() {
        var technicians = technicianService.findAll();
        var techniciansDTO = technicians.stream().map(TechnicianDTO::new).toList();
        return ResponseEntity.ok(techniciansDTO);
    }

    @PostMapping("/create")
    public ResponseEntity<TechnicianDTO> create(@RequestBody TechnicianDTO technicianDTO, UriComponentsBuilder uriComponentsBuilder) {
        var technician = technicianService.create(technicianDTO);

        var uri = uriComponentsBuilder.path("/technicians/{id}").buildAndExpand(technician.getId()).toUri();
        return ResponseEntity.created(uri).body(new TechnicianDTO(technician));
    }

}
