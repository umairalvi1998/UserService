package com.example.userservice.Models;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class User  extends BaseModel {
    private String name;
    private String email;
    private String hashedpassword;
    @ManyToMany
    private List<Roles> roles;
    private boolean isEmailVerified;

}
