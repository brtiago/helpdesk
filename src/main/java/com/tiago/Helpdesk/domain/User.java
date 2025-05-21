package com.tiago.Helpdesk.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tiago.Helpdesk.controller.dto.UserDTO;
import com.tiago.Helpdesk.enums.Profile;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends Person{
    private static final long serialVersionUID = 1L;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private final List<Ticket> tickets = new ArrayList<>();

    public User() {
        super();
        addProfiles(Profile.CLIENT);
    }

    public User(Integer id, String name, String cpf, String email, String password) {
        super(id, name, cpf, email, password);
        addProfiles(Profile.CLIENT);
    }

    public User(UserDTO userDTO) {
        id = userDTO.id();
        name = userDTO.name();
        cpf = userDTO.cpf();
        email = userDTO.email();
        password = userDTO.password();
        addProfiles(Profile.CLIENT);
    }

}
