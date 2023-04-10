package com.example.startedspringbootaplication.model.role;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public enum Role implements GrantedAuthority {
    ADMIN,
    STUDENT,
    TEACHER,
    ;


    @Override
    public String getAuthority() {
        return name();
    }
}