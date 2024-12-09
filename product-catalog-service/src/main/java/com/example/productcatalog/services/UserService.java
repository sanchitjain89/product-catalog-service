package com.example.productcatalog.services;

import com.example.productcatalog.models.User;
import com.example.productcatalog.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService  {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername(String username)  {
        User user = userRepository.findByUsername(username).orElse(null);

        if (user != null) {

            // Map your `User` entity to `UserDetails`
            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getUsername())
                    .password(user.getPassword()) // Plaintext password
                    .roles(user.getRole()) // Roles like ADMIN or USER
                    .build();
        }

        return null;
    }
}
