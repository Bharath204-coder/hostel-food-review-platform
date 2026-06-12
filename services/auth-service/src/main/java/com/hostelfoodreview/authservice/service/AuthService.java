package com.hostelfoodreview.authservice.service;

import com.hostelfoodreview.authservice.dto.AuthResponse;
import com.hostelfoodreview.authservice.entity.Role;
import com.hostelfoodreview.authservice.entity.User;
import com.hostelfoodreview.authservice.repository.UserRepository;
import com.hostelfoodreview.authservice.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public User signup(String name, String email, String password) {

        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already registered");
        }

        String hashedPassword = passwordEncoder.encode(password);

        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(hashedPassword);
        newUser.setRole(Role.STUDENT);

        return userRepository.save(newUser);
    }

    public AuthResponse login(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().toString());

        return new AuthResponse(token, user.getEmail(), user.getName(), user.getRole().toString());
    }
}