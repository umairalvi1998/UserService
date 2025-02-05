package com.example.userservice.Security.Service;

import com.example.userservice.Models.User;
import com.example.userservice.Respository.UserRepository;
import com.example.userservice.Security.Models.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    UserRepository userRepo;

    CustomUserDetailsService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        CustomUserDetails userDetails = new CustomUserDetails(user);

        return userDetails;
    }
}
