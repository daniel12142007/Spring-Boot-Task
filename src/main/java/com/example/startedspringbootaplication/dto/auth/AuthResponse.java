package com.example.startedspringbootaplication.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
// This class need for return jwt token and email
public class AuthResponse {
    private String email;
    private String jwt;
}