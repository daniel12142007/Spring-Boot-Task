package com.example.startedspringbootaplication.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "groupName")
    private String groupName;
    @Column(name = "dataStart")
    private String dataStart;
    @Column(name = "dataFinish")
    private String dataFinish;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Student> students;
    @ManyToMany
    @JoinTable(name = "course_group",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courses;


    public Groups(String groupName, String dataStart, String dataFinish) {
        this.groupName = groupName;
        this.dataStart = dataStart;
        this.dataFinish = dataFinish;
    }

    @Override
    public String toString() {
        return "Groups{" +
               "id=" + id +
               ", groupName='" + groupName + '\'' +
               ", dataStart='" + dataStart + '\'' +
               ", dataFinish='" + dataFinish + '\'' +
               '}';
    }
}