package com.example.userservice.Configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;



//@Configuration
//public class SecurityConfiguration {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.securityMatcher("/users")
//                .authorizeHttpRequests(requests -> requests
//                        .anyRequest().permitAll()  // Allow all requests
//                ).cors(AbstractHttpConfigurer::disable)              // Disable CORS for simplicity
//                .csrf(csrf -> csrf.disable());              // Disabling CSRF (for simplicity, not recommended for production)
//
//        return http.build();  // Builds and returns the configured SecurityFilterChain
//    }
//}

/*This was created, when I initiallly wanted to use Spring security for Bcrypt encoding and since spring security comes with the
defualt login/signup i.e. all the endpoints are secured by default, I had to create a custom security configuration to allow all requests.
But later I implemented OAuth2.0 and OpenID Connect 1.0 for the project, so I had to remove this configuration.
 */