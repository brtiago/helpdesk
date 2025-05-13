package com.tiago.Helpdesk.controller.dto;

import com.tiago.Helpdesk.domain.Technician;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record TechnicianDTO(
        Integer id,
        @NotBlank(message = "Name cannot be blank")
        String name,
        @NotBlank(message = "CPF cannot be blank")
        String cpf,
        @NotBlank(message = "Email cannot be blank")
        @Email(message = "Invalid email format")
        String email,
        @NotBlank(message = "Password cannot be blank")
        @Size(min = 8, message = "Password must be at least 8 characters")
        String password
        ) {

    public TechnicianDTO(Technician model){
        this(model.getId(), model.getName(), model.getCpf(), model.getEmail(), model.getPassword());
    }
}
