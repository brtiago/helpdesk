package com.tiago.Helpdesk.domain;

import com.tiago.Helpdesk.enums.Profile;

import java.util.Set;

public class User extends Person{
    private static final long serialVersionUID = 1L;

    public User() {
        super();
        addProfiles(Profile.CLIENT);
    }

    public User(Integer id, String name, String cpf, String email, String password, Set<Integer> profiles) {
        super(id, name, cpf, email, password, profiles);
        addProfiles(Profile.CLIENT);
    }
}
