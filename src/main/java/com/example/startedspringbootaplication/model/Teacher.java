package com.example.startedspringbootaplication.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name")
    private String first_name;
    @Column(name = "last_name")
    private String last_name;
    @Column(name = "email")
    private String email;
    @OneToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public Teacher(String first_name, String last_name, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Teacher{" +
               "id=" + id +
               ", first_name='" + first_name + '\'' +
               ", last_name='" + last_name + '\'' +
               ", email='" + email + '\'' +
               '}';
    }
}