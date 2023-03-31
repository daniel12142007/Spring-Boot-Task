package com.example.startedspringbootaplication.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name")
    private String first_name;
    @Column(name = "last_name")
    private String last_name;
    @Column(name = "email")
    private String email;
    @ManyToOne
    @JoinColumn(name = "groups_id")
    private Groups group;

    public Student(String first_name, String last_name, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" +
               "id=" + id +
               ", first_name='" + first_name + '\'' +
               ", last_name='" + last_name + '\'' +
               ", email='" + email + '\'' +
               '}';
    }
}