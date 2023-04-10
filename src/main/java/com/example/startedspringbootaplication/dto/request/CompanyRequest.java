package com.example.startedspringbootaplication.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CompanyRequest {
    private String email;
    private String password;
}