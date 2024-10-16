package com.tiago.Helpdesk.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tiago.Helpdesk.enums.Profile;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.FetchType;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Person  implements Serializable {

    protected Integer id;
    protected String name;
    protected String cpf;
    protected String email;
    protected String password;
    @ElementCollection(fetch = FetchType.EAGER)
    protected Set<Integer> profiles = new HashSet<>();

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate createdAt = LocalDate.now();

    public Person(Profile profile) {
        addProfiles(Profile.CLIENT);
    }

    public Person(Integer id, String name, String cpf, String email, String password, Set<Integer> profiles) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.profiles = profiles;
        addProfiles(Profile.CLIENT);
    }

    public Person() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Profile> getProfiles() {
        return profiles.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public void addProfiles(Profile profiles) {
        this.profiles.add(profiles.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id.equals(person.id) && Objects.equals(email, person.email) && createdAt.equals(person.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, createdAt);
    }
}
