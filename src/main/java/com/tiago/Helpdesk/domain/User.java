package com.tiago.Helpdesk.domain;

import com.tiago.Helpdesk.enums.Profile;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class User extends Person{
    private static final long serialVersionUID = 1L;
    @OneToMany(mappedBy = "user")
    private List<Ticket> tickets = new ArrayList<>();

    public User() {
        super();
        addProfiles(Profile.CLIENT);
    }

    public User(Integer id, String name, String cpf, String email, String password, Set<Integer> profiles) {
        super(id, name, cpf, email, password, profiles);
        addProfiles(Profile.CLIENT);
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
