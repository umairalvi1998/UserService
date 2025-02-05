package com.example.userservice.Security.Models;

import com.example.userservice.Models.Roles;
import com.example.userservice.Models.User;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@JsonDeserialize
@Component
public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private List<CustomGrantedAuthority> authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private  Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public CustomUserDetails() {
    }

    public CustomUserDetails(User user) {
        this.username = user.getEmail();
        this.password = user.getHashedpassword();
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
       // this.authorities = user.getRoles().stream().map(CustomGrantedAuthority::new).toList();
        List<CustomGrantedAuthority> authorities = new ArrayList<>();
        for (Roles role : user.getRoles()) {
            authorities.add(new CustomGrantedAuthority(role));
        }
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
