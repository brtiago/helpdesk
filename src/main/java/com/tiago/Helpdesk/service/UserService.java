package com.tiago.Helpdesk.service;

import com.tiago.Helpdesk.controller.dto.UserDTO;
import com.tiago.Helpdesk.domain.User;
import com.tiago.Helpdesk.repository.PersonRepository;
import com.tiago.Helpdesk.repository.UserRepository;
import com.tiago.Helpdesk.service.exception.DatabaseException;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;

@Service
public class UserService extends BaseService{
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository, PersonRepository personRepository) {
        this.userRepository = userRepository;
        this.personRepository = personRepository;
    }

    public User findById(Integer id) {
        validateId(id, "user");
        return userRepository.findById(id).
                orElseThrow(() -> new ResolutionException("User not found for ID: " + id));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User create (UserDTO userDTO) {
        if(userDTO == null) {
            throw new IllegalArgumentException("UserDTO cannot be null");
        }

        validateCpfEmail(userDTO.id(), userDTO.cpf(), userDTO.email());
        return userRepository.save(new User(userDTO));
    }

    public User update (Integer id, UserDTO userDTO) {
        validateCpfEmail(userDTO.id(), userDTO.cpf(), userDTO.email());
        User existingUser = findById(id);

        existingUser.setName(userDTO.name());
        existingUser.setCpf(userDTO.cpf());
        existingUser.setEmail(userDTO.email());
        existingUser.setPassword(userDTO.password());

        return userRepository.save(existingUser);
    }

    public void delete(Integer id) {
        User userFound = findById(id);

        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new DatabaseException("Error deleting user");
        }
    }
}
