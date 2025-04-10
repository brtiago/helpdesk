package com.tiago.Helpdesk.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tiago.Helpdesk.controller.dto.TechnicianDTO;
import com.tiago.Helpdesk.enums.Profile;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Technician extends Person{
    private static final long serialVersionUID = 1L;
    @JsonIgnore
    @OneToMany(mappedBy = "technician")
    private List<Ticket> tickets = new ArrayList<>();

    public Technician() {
        super();
        addProfiles(Profile.TECHNICIAN);
    }

    public Technician(TechnicianDTO technicianDTO) {
        id = technicianDTO.id();
        name = technicianDTO.name();
        cpf = technicianDTO.cpf();
        email = technicianDTO.email();
        password = technicianDTO.password();
        addProfiles(Profile.TECHNICIAN);
    }

    public Technician(Integer id, String name, String cpf, String email, String password) {
        super(id, name, cpf, email, password);
        addProfiles(Profile.TECHNICIAN);
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}