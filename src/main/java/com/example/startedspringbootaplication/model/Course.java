package com.example.startedspringbootaplication.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //    private String email;
    private String courseName;
    private String duration;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    @OneToOne(mappedBy = "course", cascade = CascadeType.ALL)
    private Teacher teacher;
    @ManyToMany(
            mappedBy = "courses",
            cascade = CascadeType.ALL)
    private List<Group> groups;

    public Course(String courseName, String duration) {
        this.courseName = courseName;
        this.duration = duration;
    }

    //    public Course(String email) {
//        this.email = email;
//    }
}