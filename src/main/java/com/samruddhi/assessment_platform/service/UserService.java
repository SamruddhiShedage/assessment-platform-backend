package com.samruddhi.assessment_platform.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.samruddhi.assessment_platform.dto.LoginRequest;
import com.samruddhi.assessment_platform.entity.User;
import com.samruddhi.assessment_platform.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User registerUser(User user) {

        Optional<User> existingUser =
                userRepository.findByEmail(user.getEmail());

        if (existingUser.isPresent()) {

            throw new RuntimeException("Email already registered");

        }

        String encryptedPassword =
                passwordEncoder.encode(user.getPassword());

        user.setPassword(encryptedPassword);

        return userRepository.save(user);

    }

    public User loginUser(LoginRequest loginRequest) {

        Optional<User> userOptional =
                userRepository.findByEmail(loginRequest.getEmail());

        if (userOptional.isEmpty()) {

            throw new RuntimeException("User not found");

        }

        User user = userOptional.get();

        if (!passwordEncoder.matches(
                loginRequest.getPassword(),
                user.getPassword())) {

            throw new RuntimeException("Invalid password");

        }

        return user;

    }

    public List<User> getAllUsers() {

        return userRepository.findAll();

    }

    public User getUserById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

    }

    public void deleteUser(Long id) {

        if (!userRepository.existsById(id)) {

            throw new RuntimeException("User not found");

        }

        userRepository.deleteById(id);

    }

}