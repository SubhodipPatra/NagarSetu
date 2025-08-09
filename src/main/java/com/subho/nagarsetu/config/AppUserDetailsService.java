package com.subho.nagarsetu.config;

import com.subho.nagarsetu.model.User;
import com.subho.nagarsetu.model.Officer;
import com.subho.nagarsetu.repo.UserRepository;
import com.subho.nagarsetu.repo.OfficerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private OfficerRepository officerRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        if (email.equals("admin@nagarsetu.com")) {
            return org.springframework.security.core.userdetails.User
                    .withUsername("admin@nagarsetu.com")
                    .password(new BCryptPasswordEncoder().encode("admin123"))
                    .roles("ADMIN")
                    .build();
        }

        // Check User table first
        return userRepo.findByEmail(email)
                .map(u -> org.springframework.security.core.userdetails.User
                        .withUsername(u.getEmail())
                        .password(u.getPassword())
                        .roles("USER")
                        .build())
                .or(() -> officerRepo.findByEmail(email)
                        .map(o -> {
                            if (!o.isApproved()) {
                                throw new UsernameNotFoundException("Officer not approved");
                            }
                            return org.springframework.security.core.userdetails.User
                                    .withUsername(o.getEmail())
                                    .password(o.getPassword())
                                    .roles("OFFICER")
                                    .build();
                        }))
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
    }
}
