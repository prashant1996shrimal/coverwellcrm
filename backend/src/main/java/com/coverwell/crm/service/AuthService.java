package com.coverwell.crm.service;

import org.springframework.stereotype.Service;

import com.coverwell.crm.entity.User;
import com.coverwell.crm.repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElse(null);

        // Email not found
        if (user == null) {
            throw new RuntimeException("Invalid email or password");
        }

        // Password incorrect
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid email or password");
        }

        // Account inactive
        if (!user.isActive()) {
            throw new RuntimeException("User account is inactive");
        }

        return user;
    }
}