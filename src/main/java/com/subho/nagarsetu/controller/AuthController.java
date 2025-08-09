package com.subho.nagarsetu.controller;

import com.subho.nagarsetu.dto.LoginRequest;
import com.subho.nagarsetu.config.AppUserDetailsService;
import com.subho.nagarsetu.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AppUserDetailsService userDetailsService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            UserDetails user = userDetailsService.loadUserByUsername(request.getEmail());
            System.out.println(user);
            if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                String jwt = jwtService.generateToken(user);
                return ResponseEntity.ok().body(Map.of("token", jwt));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        } catch (Exception e) {
            e.printStackTrace(); // 👈 This will show the real error in your console/logs
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed: " + e.getMessage());
        }

    }
}

