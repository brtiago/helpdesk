package com.tiago.Helpdesk.service;

import com.tiago.Helpdesk.controller.dto.UserDTO;
import com.tiago.Helpdesk.controller.dto.UserRequest;
import com.tiago.Helpdesk.domain.User;
import com.tiago.Helpdesk.repository.UserRepository;
import com.tiago.Helpdesk.service.exception.DatabaseException;
import com.tiago.Helpdesk.service.exception.ResourceNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
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

    public UserDTO findById(Integer id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for ID: " + id));
        return new UserDTO(existingUser.getId(), existingUser.getName(), existingUser.getCpf(), existingUser.getEmail()
        );
    }

    public Page<UserDTO> findAll(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(UserDTO::new);
    }

    public UserDTO create (UserRequest userRequest) {
        if(userRequest == null) {
            throw new IllegalArgumentException("UserRequest cannot be null");
        }

        validationService.validateCpfEmail(null, userRequest.cpf(), userRequest.email());

        User user = new User();
        user.setName(userRequest.name());
        user.setEmail(userRequest.email());
        user.setCpf(userRequest.cpf());
        user.setPassword(userRequest.password());

        User savedUser= userRepository.save(user);

        return new UserDTO(savedUser.getId(), savedUser.getName(), savedUser.getCpf(), savedUser.getEmail());
    }

    public UserDTO update (Integer id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for ID: " + id));

        validationService.validateCpfEmail(existingUser.getId(), existingUser.getCpf(), existingUser.getEmail());


        existingUser.setName(userDTO.name());
        existingUser.setCpf(userDTO.cpf());
        existingUser.setEmail(userDTO.email());
        existingUser.setPassword(userDTO.password());

        User updatedUser = userRepository.save(existingUser);

        return UserDTO.fromUser(updatedUser);
    }

    public void delete(Integer id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("User not found for ID: " + id);
        } catch (Exception e) {
            throw new DatabaseException("Failed to delete user with ID: " + id + ". Reason: " + e.getMessage());
        }
    }
}
