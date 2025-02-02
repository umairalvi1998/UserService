package com.example.userservice.Controllers;

import com.example.userservice.DTOs.*;
import com.example.userservice.Exceptions.UserAlreadExistsException;
import com.example.userservice.Models.Token;
import com.example.userservice.Models.User;
import com.example.userservice.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto>  signUp(@RequestBody SignupRequestDto requestDto) throws UserAlreadExistsException {
         User user =  userService.signUp(requestDto.getName(), requestDto.getEmail(), requestDto.getPassword());

          return new ResponseEntity<>(UserDto.from(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto requestDto) {
        Token token = userService.login(requestDto.getEmail(), requestDto.getPassword());
        LoginResponseDto responseDto = new LoginResponseDto();
        responseDto.setToken(token);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logOut(@RequestBody LogOutRequestDto requestDto) {
        return null;
    }
}
