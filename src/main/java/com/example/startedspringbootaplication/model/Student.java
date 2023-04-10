package com.example.startedspringbootaplication.model;

import com.example.startedspringbootaplication.model.role.Role;
import com.example.startedspringbootaplication.model.role.StudentFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private StudentFormat studentFormat;
    @ManyToOne
    @JoinColumn(name = "groups_id")
    private Groups group;

    public Student(String email, String password, Role role,StudentFormat studentFormat) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.studentFormat=studentFormat;
    }
}