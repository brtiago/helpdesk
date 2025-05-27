package com.tiago.Helpdesk.service;

import com.tiago.Helpdesk.repository.PersonRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    protected PersonRepository personRepository;

    protected ValidationService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void validateId(Integer id, String entityName){
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid " + entityName + " ID: " + id);
        }
    }

    public void validateCpfEmail(Integer id, String cpf, String email ) {
        personRepository.findByCpf(cpf)
                .ifPresent(person -> {
                    if (id == null || !person.getId().equals(id)) {
                        throw new DataIntegrityViolationException("CPF já cadastrado!");
                    }
                });

        personRepository.findByEmail(email)
                .ifPresent(person -> {
                    if (id == null || !person.getId().equals(id)) {
                        throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
                    }
                });
    }

}
