package com.tiago.Helpdesk.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tiago.Helpdesk.controller.dto.UserRequest;
import com.tiago.Helpdesk.enums.Profile;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class User extends Person{
    private static final long serialVersionUID = 1L;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private final List<Ticket> tickets = new ArrayList<>();

    public User(Integer id, String name, String cpf, String email, String password, Set<Integer> roles) {
    }

    public static class Builder {
        private Integer id;
        private String name;
        private String cpf;
        private String email;
        private String password;
        private Set<Integer> profiles = new HashSet<>();
        private LocalDate createdAt;

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

        public Builder withProfiles(Set<Integer> roles) {
            this.profiles = roles;
            return this;
        }

        public User build() {
            return new User(id, name, cpf, email, password, profiles);
        }

    }

    public User() {
        super();
        addProfiles(Profile.CLIENT);
    }

    public User(Integer id, String name, String cpf, String email, String password) {
        super(id, name, cpf, email, password);
        addProfiles(Profile.CLIENT);
    }

    public User(UserRequest userRequest) {
        id = userRequest.id();
        name = userRequest.name();
        cpf = userRequest.cpf();
        email = userRequest.email();
        password = userRequest.password();
        addProfiles(Profile.CLIENT);
    }

}
