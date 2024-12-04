package com.tiago.Helpdesk.repository;

import com.tiago.Helpdesk.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
