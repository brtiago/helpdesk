package com.tiago.Helpdesk.controller;

import com.tiago.Helpdesk.controller.dto.UserDTO;
import com.tiago.Helpdesk.controller.dto.UserRequest;
import com.tiago.Helpdesk.domain.User;
import com.tiago.Helpdesk.repository.UserRepository;
import com.tiago.Helpdesk.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Integer id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(new UserDTO(user));
    }

    @GetMapping
    public ResponseEntity<Page<UserDTO>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<User> usersPage = userService.findAll(pageable);
        Page<UserDTO> dtoPage = usersPage.map(UserDTO::new);

        return ResponseEntity.ok(dtoPage);
    }

    @PostMapping
    public ResponseEntity<?> create(
            @Valid @RequestBody UserRequest userRequest,
            UriComponentsBuilder uriComponentsBuilder) {

        User user = userService.create(userRequest);
        var uri = uriComponentsBuilder.path("/api/v1/users/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserDTO(user));

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> update(
            @PathVariable Integer id,
            @Valid @RequestBody UserDTO userDTO) {

        if (!id.equals(userDTO.id())) {
            throw new IllegalArgumentException("id mismatch");
        }

        User updatedUser = userService.update(id, userDTO);
        return ResponseEntity.ok(new UserDTO(updatedUser));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
