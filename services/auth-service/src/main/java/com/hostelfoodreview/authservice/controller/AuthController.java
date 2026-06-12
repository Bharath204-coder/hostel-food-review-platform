package com.hostelfoodreview.authservice.controller;

import com.hostelfoodreview.authservice.dto.AuthResponse;
import com.hostelfoodreview.authservice.dto.LoginRequest;
import com.hostelfoodreview.authservice.dto.SignupRequest;
import com.hostelfoodreview.authservice.entity.User;
import com.hostelfoodreview.authservice.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
        User user = authService.signup(request.getName(), request.getEmail(), request.getPassword());
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(response);
    }
}