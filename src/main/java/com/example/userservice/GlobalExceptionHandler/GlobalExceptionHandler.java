package com.example.userservice.GlobalExceptionHandler;

import com.example.userservice.Exceptions.InvalidPasswordException;
import com.example.userservice.Exceptions.SessionExpiredException;
import com.example.userservice.Exceptions.UserAlreadExistsException;
import com.example.userservice.Exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExistsException( UserAlreadExistsException ex ) {
        return new ResponseEntity<>("User already exists: " + ex.getMessage(), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException( UserNotFoundException ex ) {
        return new ResponseEntity<>("User not found: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<String> handleInvalidPasswordException( InvalidPasswordException ex ) {
        return new ResponseEntity<>("Invalid password: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SessionExpiredException.class)
    public ResponseEntity<String> handleSessionExpiredException( SessionExpiredException ex ) {
        return new ResponseEntity<>("Session Expired: " + ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
