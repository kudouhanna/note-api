package com.example.noteapi.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class User {
    private long userId;
    private String username;
    private String password;
    private LocalDate createdAt;
}
