package com.example.productcatalog;

import com.example.productcatalog.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for APIs
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/swagger-ui/**",      // Swagger UI static resources
                                "/v3/api-docs/**",     // OpenAPI spec resources
                                "/webjars/**",         // Swagger UI webjars
                                "/swagger-resources/**", // Swagger resource endpoints
                                "/h2-console/**",
                                "/h2-ui/**"
                        ).permitAll() // Completely disable security for Swagger
                        .requestMatchers("/categories/**").permitAll() // Public endpoints
                        .requestMatchers("/products/**").hasRole("ADMIN") // Admin-only endpoints
                )
                .httpBasic(httpBasic -> {}); // Use HTTP Basic authentication for simplicity

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> userRepository.findByUsername(username)
                .map(user -> org.springframework.security.core.userdetails.User
                        .withUsername(user.getUsername())
                        .password(user.getPassword()) // Return plaintext password
                        .roles(user.getRole())
                        .build()
                )
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();  // Skip encoding for testing
    }
}

