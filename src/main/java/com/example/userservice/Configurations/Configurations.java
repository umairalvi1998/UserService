package com.example.userservice.Configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class Configurations {

    @Bean
    BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
