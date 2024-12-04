package com.tiago.Helpdesk.repository;

import com.tiago.Helpdesk.domain.Ticket;
import com.tiago.Helpdesk.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
