package com.tiago.Helpdesk.controller.dto;

import com.tiago.Helpdesk.domain.Technician;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record TechnicianDTO(
        Integer id,
        @NotNull(message = "The field Name is required.")
        String name,
        @NotNull(message = "The field Cpf is required.")
        String cpf,
        @NotNull(message = "The field Email is required.")
        @Email
        String email,
        @NotNull
        String password
        ) {

    public TechnicianDTO(Technician model){
        this(model.getId(), model.getName(), model.getCpf(), model.getEmail(), model.getPassword());
    }
}
