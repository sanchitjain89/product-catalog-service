package com.example.productcatalog.services;

import com.example.productcatalog.models.User;
import com.example.productcatalog.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService  {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User loadUserByUsername(String username)  {
        User user = userRepository.findByUsername(username).orElse(null);

        return user;
    }
}
