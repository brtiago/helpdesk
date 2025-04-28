package com.tiago.Helpdesk.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tiago.Helpdesk.controller.dto.TechnicianDTO;
import com.tiago.Helpdesk.enums.Profile;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Technician extends Person{
    private static final long serialVersionUID = 1L;
    @JsonIgnore
    @OneToMany(mappedBy = "technician")
    private List<Ticket> tickets = new ArrayList<>();

    public static class Builder {
        private Integer id;
        private String name;
        private String cpf;
        private String email;
        private String password;
        private Set<Integer> profiles = new HashSet<>();

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withCpf(String cpf) {
            this.cpf = cpf;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withProfiles(Set<Integer> profiles) {
            this.profiles = profiles;
            return this;
        }

        public Technician build() {
            return new Technician(id, name, cpf, email, password, profiles);
        }
    }

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

    public Technician(Integer id, String name, String cpf, String email, String password, Set<Integer> profiles) {
        super(id, name, cpf, email, password);
        this.profiles = profiles;
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