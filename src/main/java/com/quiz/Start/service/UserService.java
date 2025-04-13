package com.quiz.Start.service;

import com.quiz.Start.DTO.LoginRequest;
import com.quiz.Start.DTO.LoginResponse;
import com.quiz.Start.Exceptions.UserAlreadyExistsException;
import com.quiz.Start.model.User;
import com.quiz.Start.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User createUser(User user){
        // Check if user with this email already exists
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException("Email already registered");
        }
        return userRepository.save(user);
    }

    public LoginResponse authenticateUser(LoginRequest loginRequest) {
        // Find user by email
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check password (in real app, use password encoder)
        if (!user.getPassword().equals(loginRequest.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return new LoginResponse(
                user.getEmail(),
                "Login successful",
                true
        );
    }
}
