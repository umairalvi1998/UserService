package com.example.userservice.Service;

import com.example.userservice.DTOs.UserDto;
import com.example.userservice.Exceptions.InvalidPasswordException;
import com.example.userservice.Exceptions.UserAlreadExistsException;
import com.example.userservice.Exceptions.UserNotFoundException;
import com.example.userservice.Models.Token;
import com.example.userservice.Models.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    public User signUp(String name, String email, String password) throws UserAlreadExistsException;
    public Token login(String username, String password) throws UserNotFoundException, InvalidPasswordException;
    public Void logOut(String token);
}
