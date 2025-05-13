package com.tiago.Helpdesk.controller;

import java.util.List;

import com.tiago.Helpdesk.repository.TechnicianRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private final TechnicianRepository technicianRepository;

    public TechnicianController(TechnicianService technicianService, TechnicianRepository technicianRepository){
        this.technicianService = technicianService;
        this.technicianRepository = technicianRepository;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TechnicianDTO> findById(@PathVariable Integer id) {
        Technician technician = technicianService.findById(id);
        TechnicianDTO technicianDTO = new TechnicianDTO(technician);
        return ResponseEntity.ok().body(technicianDTO);
    }

    @GetMapping
    public ResponseEntity<Page<TechnicianDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Technician> result = technicianRepository.findAll(pageable);
        Page<TechnicianDTO> technicianDTOPage = result.map(TechnicianDTO::new);

        return ResponseEntity.ok().body(technicianDTOPage);
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
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        technicianService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
