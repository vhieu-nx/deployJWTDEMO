package com.example.be.service;

import com.example.be.model.User;

import java.util.Optional;

public interface IUserService {
    Optional<User> findByUsername(String username);
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);
    User save(User user);
}
