package com.tiago.Helpdesk.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.tiago.Helpdesk.controller.dto.TechnicianDTO;
import com.tiago.Helpdesk.domain.Technician;
import com.tiago.Helpdesk.service.TechnicianService;

import jakarta.validation.Valid;

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

    @PostMapping
    public ResponseEntity<TechnicianDTO> create(@Valid @RequestBody TechnicianDTO technicianDTO, UriComponentsBuilder uriComponentsBuilder) {
        var technician = technicianService.create(technicianDTO);

        var uri = uriComponentsBuilder.path("/technicians/{id}").buildAndExpand(technician.getId()).toUri();
        return ResponseEntity.created(uri).body(new TechnicianDTO(technician));
    }

}
