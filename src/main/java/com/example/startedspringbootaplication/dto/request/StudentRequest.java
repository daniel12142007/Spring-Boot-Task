package com.example.startedspringbootaplication.dto.request;

import com.example.startedspringbootaplication.model.enums.StudentFormat;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StudentRequest {
    private String email;
    private String password;
    private StudentFormat studentFormat;
}