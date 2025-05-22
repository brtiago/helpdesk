package com.tiago.Helpdesk.controller;

import com.tiago.Helpdesk.controller.dto.UserDTO;
import com.tiago.Helpdesk.domain.User;
import com.tiago.Helpdesk.repository.UserRepository;
import com.tiago.Helpdesk.service.UserService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.hibernate.validator.internal.constraintvalidators.bv.size.SizeValidatorForArray;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.support.UriComponentsContributor;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final SizeValidatorForArray sizeValidatorForArray;
    private final UriComponentsContributor uriComponentsContributor;

    public UserController(UserService userService, UserRepository userRepository, SizeValidatorForArray sizeValidatorForArray, UriComponentsContributor uriComponentsContributor) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.sizeValidatorForArray = sizeValidatorForArray;
        this.uriComponentsContributor = uriComponentsContributor;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Integer id) {
        User user = userService.findById(id);
        UserDTO userDTO = new UserDTO(user);
        return ResponseEntity.ok().body(userDTO);
    }

    @GetMapping
    public ResponseEntity<Page<UserDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<User> result = userRepository.findAll(pageable);
        Page<UserDTO> userDTOPage = result.map(UserDTO::new);

        return ResponseEntity.ok().body(userDTOPage);
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO userDTO, UriComponentsBuilder uriComponentsBuilder) {
        var user = userService.create(userDTO);

        var uri = uriComponentsBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserDTO(user));

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Integer id, @Valid @RequestBody UserDTO userDTO) {
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
