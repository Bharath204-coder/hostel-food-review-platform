package com.hostelfoodreview.authservice.service;

import com.hostelfoodreview.authservice.entity.Role;
import com.hostelfoodreview.authservice.entity.User;
import com.hostelfoodreview.authservice.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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

    public User login(String email, String password){

        User user = userRepository.findByEmail(email)
                    .orElseThrow( () -> new RuntimeException("User not found"));

        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }
        return user;          
    }
}


