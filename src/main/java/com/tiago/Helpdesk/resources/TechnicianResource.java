package com.tiago.Helpdesk.resources;

import com.tiago.Helpdesk.domain.Technician;
import com.tiago.Helpdesk.service.TechnicianService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/technician")
public class TechnicianResource {

    private final TechnicianService technicianService;

    public TechnicianResource(TechnicianService technicianService) {
        this.technicianService = technicianService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Technician> findById(@PathVariable Integer id) {
        Technician technician = technicianService.findId(id);

        return ResponseEntity.ok().body(technician);
    }

}
