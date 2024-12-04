package com.tiago.Helpdesk.service;

import com.tiago.Helpdesk.domain.Technician;
import com.tiago.Helpdesk.repository.TechnicianRepository;
import com.tiago.Helpdesk.service.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TechnicianService {

    private final TechnicianRepository technicianRepository;

    public TechnicianService(TechnicianRepository technicianRepository){
        this.technicianRepository = technicianRepository;
    }

    public Technician findById(Integer id) {
        isIdValid(id);
        return technicianRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Technician not found for ID: " + id));
    }
    private void isIdValid(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid ID: " + id);
        }
    }
}
