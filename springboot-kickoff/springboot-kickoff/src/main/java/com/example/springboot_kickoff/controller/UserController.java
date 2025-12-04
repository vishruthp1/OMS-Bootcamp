package com.example.springboot_kickoff.controller;

import com.example.springboot_kickoff.model.User;
import com.example.springboot_kickoff.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello from Spring Boot!";
    }

    @PostMapping("/api/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User saved = service.create(user);
        return ResponseEntity.created(URI.create("/api/users/" + saved.getId())).body(saved);
    }

    @GetMapping("/api/users")
    public List<User> getUsers() {
        return service.listAll();
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return service.getById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}

