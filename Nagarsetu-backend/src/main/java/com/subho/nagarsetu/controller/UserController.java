package com.subho.nagarsetu.controller;

import com.subho.nagarsetu.model.Officer;
import com.subho.nagarsetu.model.User;
import com.subho.nagarsetu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id).orElse(null);
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getOfficerDetails(Authentication auth) {
        String email = auth.getName(); // or however you're identifying the officer
        User user =userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }
}
