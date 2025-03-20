package com.tiago.Helpdesk.controller.dto;

import com.tiago.Helpdesk.domain.Technician;
import com.tiago.Helpdesk.enums.Profile;

import java.util.Set;

public record TechnicianDTO(
        Integer id,
        String name,
        String cpf,
        String email,
        String password
        ) {

    public TechnicianDTO(Technician model){
        this(model.getId(), model.getName(), model.getCpf(), model.getEmail(), model.getPassword());
    }
}
