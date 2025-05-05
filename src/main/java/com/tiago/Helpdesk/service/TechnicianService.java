package com.tiago.Helpdesk.service;

import com.tiago.Helpdesk.controller.dto.TechnicianDTO;
import com.tiago.Helpdesk.domain.Technician;
import com.tiago.Helpdesk.repository.PersonRepository;
import com.tiago.Helpdesk.repository.TechnicianRepository;
import com.tiago.Helpdesk.service.exception.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TechnicianService {

    private final TechnicianRepository technicianRepository;
    private final PersonRepository personRepository;

    public TechnicianService(TechnicianRepository technicianRepository, PersonRepository personRepository){
        this.technicianRepository = technicianRepository;
        this.personRepository = personRepository;
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
        return technicianRepository.findAll();
    }

    public Technician create(TechnicianDTO technicianDTO) {
        if (technicianDTO == null) {
            throw new IllegalArgumentException("TechnicianDTO cannot be null");
        }

        validateCpfEmail(technicianDTO);
        return technicianRepository.save(new Technician(technicianDTO));
    }

    public Technician update(Integer id, TechnicianDTO technicianDTO) {
        validateCpfEmail(technicianDTO);
        Technician existingTechnician = findId(id);

        existingTechnician.setName(technicianDTO.name());
        existingTechnician.setCpf(technicianDTO.cpf());
        existingTechnician.setEmail(technicianDTO.email());
        existingTechnician.setPassword(technicianDTO.password());

        return technicianRepository.save(existingTechnician);
    }

    private void validateCpfEmail(TechnicianDTO technicianDTO) {

        personRepository.findByCpf(technicianDTO.cpf())
                .ifPresent(person -> {
                    if (technicianDTO.id() == null || !person.getId().equals(technicianDTO.id())) {
                        throw new DataIntegrityViolationException("CPF já cadastrado!");
                    }
                });

        personRepository.findByEmail(technicianDTO.email())
                .ifPresent(person -> {
                    if (technicianDTO.id() == null || !person.getId().equals(technicianDTO.id())) {
                        throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
                    }
                });
    }



}
