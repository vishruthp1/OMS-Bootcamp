package com.example.springboot_kickoff.repository;

import com.example.springboot_kickoff.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // extra finder examples:
    // Optional<User> findByEmail(String email);
}
