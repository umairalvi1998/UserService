package com.example.userservice.DTOs;

import com.example.userservice.Models.Token;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {
    Token token;
}
