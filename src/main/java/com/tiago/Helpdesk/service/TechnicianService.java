package com.tiago.Helpdesk.service;

import com.tiago.Helpdesk.domain.Technician;
import com.tiago.Helpdesk.repository.TechnicianRepository;
import com.tiago.Helpdesk.service.exception.DatabaseException;
import com.tiago.Helpdesk.service.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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

     public Technician findId(Integer id) {
        Optional<Technician> technician = technicianRepository.findById(id);
        return technician.orElse(null);
     }

    public List<Technician> findAll() {
        try {
            List<Technician> technicians = this.technicianRepository.findAll();
            return technicians != null ? technicians : new ArrayList<>();
        } catch (Exception e) {
            throw new DatabaseException("Failed to fetch technicians");
        }
    }
}
