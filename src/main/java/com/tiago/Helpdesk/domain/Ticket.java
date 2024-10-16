package com.tiago.Helpdesk.domain;

import com.tiago.Helpdesk.domain.enums.Priority;
import com.tiago.Helpdesk.domain.enums.Status;

import java.time.LocalDate;
import java.util.Objects;

public class Ticket {

    private Integer id;
    private LocalDate openedAt = LocalDate.now();
    private LocalDate closedAt;
    private Priority priority;
    private Status status;
    private String title;
    private String notes;

    private Technician technician;
    private User user;

    public Ticket(Integer id, Priority priority, Status status, String title, String notes, Technician technician, User user) {
        this.id = id;
        this.priority = priority;
        this.status = status;
        this.title = title;
        this.notes = notes;
        this.technician = technician;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getOpenedAt() {
        return openedAt;
    }

    public void setOpenedAt(LocalDate openedAt) {
        this.openedAt = openedAt;
    }

    public LocalDate getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(LocalDate closedAt) {
        this.closedAt = closedAt;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Technician getTechnician() {
        return technician;
    }

    public void setTechnician(Technician technician) {
        this.technician = technician;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id.equals(ticket.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
