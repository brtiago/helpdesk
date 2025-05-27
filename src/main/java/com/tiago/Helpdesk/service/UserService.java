package com.tiago.Helpdesk.service;

import com.tiago.Helpdesk.controller.dto.UserDTO;
import com.tiago.Helpdesk.controller.dto.UserRequest;
import com.tiago.Helpdesk.domain.User;
import com.tiago.Helpdesk.repository.UserRepository;
import com.tiago.Helpdesk.service.exception.DatabaseException;
import com.tiago.Helpdesk.service.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ValidationService validationService;

    public UserService(UserRepository userRepository, ValidationService validationService) {
        this.userRepository = userRepository;
        this.validationService = validationService;
    }

    public User findById(Integer id) {
        validationService.validateId(id, "user");
        return userRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("User not found for ID: " + id));
    }

    public Page<User> findAll(Pageable pageable) {

        return userRepository.findAll(pageable);
    }

    public User create (UserRequest userRequest) {
        if(userRequest == null) {
            throw new IllegalArgumentException("UserRequest cannot be null");
        }

        validationService.validateCpfEmail(userRequest.id(), userRequest.cpf(), userRequest.email());
        return userRepository.save(new User(userRequest));
    }

    public User update (Integer id, UserDTO userDTO) {
        validationService.validateCpfEmail(userDTO.id(), userDTO.cpf(), userDTO.email());
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
