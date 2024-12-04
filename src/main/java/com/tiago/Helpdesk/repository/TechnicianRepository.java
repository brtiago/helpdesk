package com.tiago.Helpdesk.repository;

import com.tiago.Helpdesk.domain.Technician;
import com.tiago.Helpdesk.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnicianRepository extends JpaRepository<Technician, Integer> {
}
