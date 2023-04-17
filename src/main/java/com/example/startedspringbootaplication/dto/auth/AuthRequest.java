package com.example.startedspringbootaplication.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// This class need for login  
public class AuthRequest {
    private String email;
    private String password;
}