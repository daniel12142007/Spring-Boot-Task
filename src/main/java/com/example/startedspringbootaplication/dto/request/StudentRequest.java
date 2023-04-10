package com.example.startedspringbootaplication.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StudentRequest {
    private String email;
    private String password;
}