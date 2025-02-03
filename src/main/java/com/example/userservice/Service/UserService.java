package com.example.userservice.Service;

import com.example.userservice.Exceptions.InvalidPasswordException;
import com.example.userservice.Exceptions.SessionExpiredException;
import com.example.userservice.Exceptions.UserAlreadExistsException;
import com.example.userservice.Exceptions.UserNotFoundException;
import com.example.userservice.Models.Token;
import com.example.userservice.Models.User;

public interface UserService {
    public User signUp(String name, String email, String password) throws UserAlreadExistsException;
    public Token login(String username, String password) throws UserNotFoundException, InvalidPasswordException;
    public void logOut(String token) throws SessionExpiredException;
    public User validateToken(String token) throws SessionExpiredException;
}
