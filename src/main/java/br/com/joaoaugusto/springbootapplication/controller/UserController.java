package br.com.joaoaugusto.springbootapplication.controller;

import br.com.joaoaugusto.springbootapplication.dto.DeposityDto;
import br.com.joaoaugusto.springbootapplication.dto.UserDto;
import br.com.joaoaugusto.springbootapplication.model.User;
import br.com.joaoaugusto.springbootapplication.service.UserService;

import jakarta.validation.Valid;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    // Linkando o service

    private final UserService userService;

    // Constructor

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // CRUD

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@Valid @RequestBody final UserDto data) {
        final User createdUser = userService.createUser(data);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/read")
    public ResponseEntity<List<User>> readUsers() {
        final List<User> allUsers = userService.readUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping("/retrieve/{id}")
    public ResponseEntity<User> retrieveUser(@PathVariable final String id) {
        final User user = userService.reatrieveUser(Long.parseLong(id));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@Valid @RequestBody final UserDto data, @PathVariable final String id) {
        final User user = userService.updateUser(data, Long.parseLong(id));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable final String id) {
        userService.deleteUser(Long.parseLong(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/deposity/{id}")
    public ResponseEntity<User> createDeposity(@Valid @RequestBody DeposityDto data, @PathVariable String id) {
        final User depositedUser = userService.createDeposity(data, Long.parseLong(id));
        return new ResponseEntity<>(depositedUser, HttpStatus.OK);
    }

}
