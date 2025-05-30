package com.tiago.Helpdesk.controller;

import com.tiago.Helpdesk.controller.dto.UserDTO;
import com.tiago.Helpdesk.controller.dto.UserRequest;
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

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<UserDTO>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<UserDTO> usersPage = userService.findAll(pageable);

        return ResponseEntity.ok(usersPage);
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(
            @Valid @RequestBody UserRequest userRequest,
            UriComponentsBuilder uriComponentsBuilder) {

        UserDTO user = userService.create(userRequest);
        var uri = uriComponentsBuilder.path("/api/v1/users/{id}").buildAndExpand(user.id()).toUri();
        return ResponseEntity.created(uri).body(user);

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> update(
            @PathVariable Integer id,
            @Valid @RequestBody UserDTO userDTO) {

        if (!id.equals(userDTO.id())) {
            throw new IllegalArgumentException("id mismatch");
        }

        UserDTO updatedUser = userService.update(id, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
