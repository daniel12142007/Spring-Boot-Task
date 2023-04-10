package com.example.startedspringbootaplication.model;

import com.example.startedspringbootaplication.model.role.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
        @Enumerated(EnumType.STRING)
    private Role role;
    @OneToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public Teacher(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }
}