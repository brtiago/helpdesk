package com.tiago.Helpdesk.service;

import com.tiago.Helpdesk.repository.PersonRepository;
import org.springframework.dao.DataIntegrityViolationException;

public abstract class BaseService {

    protected PersonRepository personRepository;

    protected BaseService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    protected BaseService() {

    }

    protected void validateId(Integer id, String entityName){
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid " + entityName + " ID: " + id);
        }
    }

    protected void validateCpfEmail(Integer id, String cpf, String email ) {
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
