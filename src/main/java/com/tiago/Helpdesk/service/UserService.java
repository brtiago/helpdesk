package com.tiago.Helpdesk.service;

import com.tiago.Helpdesk.controller.dto.UserDTO;
import com.tiago.Helpdesk.domain.User;
import com.tiago.Helpdesk.repository.PersonRepository;
import com.tiago.Helpdesk.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;

@Service
public class UserService extends BaseService{
    private final UserRepository userRepository;
    private final PersonRepository personRepository;

    public UserService(UserRepository userRepository, PersonRepository personRepository) {
        this.userRepository = userRepository;
        this.personRepository = personRepository;
    }

    private void isIdValid(Integer id) {
        if(id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid user id: " + id);
        }
    }

    public User findById(Integer id) {
        validateId(id, "user");
        return userRepository.findById(id).
                orElseThrow(() -> new ResolutionException("User not found for ID: " + id));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

}
