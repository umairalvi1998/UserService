package com.example.userservice.DTOs;

import com.example.userservice.Models.Roles;
import com.example.userservice.Models.User;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto {
    private String name;
    private String email;
    @ManyToMany
    List<Roles> roles;
    private boolean isEmailVerified;

    public static UserDto from(User user) {
        UserDto userDto = new UserDto();
        userDto.name = user.getName();
        userDto.email = user.getEmail();
        userDto.isEmailVerified = user.isEmailVerified();
        userDto.roles = user.getRoles();
        return userDto;
    }
}
