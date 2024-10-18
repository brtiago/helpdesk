package com.tiago.Helpdesk.repository;

import com.tiago.Helpdesk.domain.Technician;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnicianRepository extends JpaRepository<Technician, Integer> {
}
