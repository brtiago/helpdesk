package com.tiago.Helpdesk.service;

import com.tiago.Helpdesk.controller.dto.TechnicianDTO;
import com.tiago.Helpdesk.domain.Technician;
import com.tiago.Helpdesk.repository.PersonRepository;
import com.tiago.Helpdesk.repository.TechnicianRepository;
import com.tiago.Helpdesk.service.exception.DatabaseException;
import com.tiago.Helpdesk.service.exception.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TechnicianService extends BaseService {

    private final TechnicianRepository technicianRepository;
    private final PersonRepository personRepository;

    public TechnicianService(TechnicianRepository technicianRepository, PersonRepository personRepository){

        this.technicianRepository = technicianRepository;
        this.personRepository = personRepository;
    }

    public Technician findById(Integer id) {
        validateId(id, "technician");
        return technicianRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Technician not found for ID: " + id));
    }

    public List<Technician> findAll() {
        return technicianRepository.findAll();
    }

    public Technician create(TechnicianDTO technicianDTO) {
        if (technicianDTO == null) {
            throw new IllegalArgumentException("TechnicianDTO cannot be null");
        }

        validateCpfEmail(technicianDTO.id(), technicianDTO.cpf(), technicianDTO.email());
        return technicianRepository.save(new Technician(technicianDTO));
    }

    public Technician update(Integer id, TechnicianDTO technicianDTO) {
        validateCpfEmail(technicianDTO);
        Technician existingTechnician = findById(id);

        existingTechnician.setName(technicianDTO.name());
        existingTechnician.setCpf(technicianDTO.cpf());
        existingTechnician.setEmail(technicianDTO.email());
        existingTechnician.setPassword(technicianDTO.password());

        return technicianRepository.save(existingTechnician);
    }

    public void delete(Integer id) {
        Technician technicianFound = findById(id);

        if(!technicianFound.getTickets().isEmpty()){
            throw new DataIntegrityViolationException("Technician cannot be deleted because it has associated tickets!");
        }

        try {
            technicianRepository.deleteById(id);
        } catch (Exception e) {
            throw new DatabaseException("Error deleting technician");
        }

    }
}
