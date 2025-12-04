package com.example.springboot_kickoff.service;

import com.example.springboot_kickoff.model.User;
import com.example.springboot_kickoff.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public User create(User user) {
        return repo.save(user);
    }

    public List<User> listAll() {
        return repo.findAll();
    }

    public Optional<User> getById(Long id) {
        return repo.findById(id);
    }
}

