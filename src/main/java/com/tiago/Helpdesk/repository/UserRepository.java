package com.tiago.Helpdesk.repository;

import com.tiago.Helpdesk.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
