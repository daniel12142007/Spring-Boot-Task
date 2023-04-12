package com.example.startedspringbootaplication.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameCompany;
    private String email;
    private String password;
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Course> courses;
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Group> groups;

    public Company(String nameCompany, String email, String password) {
        this.nameCompany = nameCompany;
        this.email = email;
        this.password = password;
    }
}