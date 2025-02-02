package com.example.userservice.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Token extends  BaseModel {

    private String value;
    @ManyToOne
    private User user;
    private LocalDateTime expiredAt;
    private LocalDateTime issuedAt;
}
