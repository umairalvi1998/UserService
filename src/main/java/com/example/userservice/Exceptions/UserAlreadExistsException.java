package com.example.userservice.Exceptions;

public class UserAlreadExistsException extends  Exception{

    public UserAlreadExistsException(String message) {
        super(message);
    }
}
