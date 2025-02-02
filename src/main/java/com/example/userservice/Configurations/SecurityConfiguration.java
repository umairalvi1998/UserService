package com.example.userservice.Configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(requests -> requests
                        .anyRequest().permitAll()  // Allow all requests
                ).cors(AbstractHttpConfigurer::disable)              // Disable CORS for simplicity
                .csrf(csrf -> csrf.disable());              // Disabling CSRF (for simplicity, not recommended for production)

        return http.build();  // Builds and returns the configured SecurityFilterChain
    }
}
