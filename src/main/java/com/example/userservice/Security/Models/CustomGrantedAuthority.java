package com.example.userservice.Security.Models;

import com.example.userservice.Models.Roles;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.security.core.GrantedAuthority;

@JsonDeserialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomGrantedAuthority implements GrantedAuthority {
    private String authority;

    public CustomGrantedAuthority() {
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public CustomGrantedAuthority(Roles role) {
        this.authority = role.getName();
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
