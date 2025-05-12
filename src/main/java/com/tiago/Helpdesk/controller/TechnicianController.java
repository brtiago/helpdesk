package com.tiago.Helpdesk.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @PutMapping(value = "/{id}")
    public ResponseEntity<TechnicianDTO> update(@PathVariable Integer id, @Valid @RequestBody TechnicianDTO technicianDTO) {
        if (!id.equals(technicianDTO.id())) {
            throw new IllegalArgumentException("ID na URL não corresponde ao ID no corpo");
        }

        Technician updatedTechnician = technicianService.update(id, technicianDTO);
        return ResponseEntity.ok(new TechnicianDTO(updatedTechnician));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<TechnicianDTO> delete(@PathVariable Integer id) {
        technicianService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
