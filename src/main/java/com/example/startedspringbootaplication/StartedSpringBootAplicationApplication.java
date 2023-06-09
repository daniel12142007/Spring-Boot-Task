package com.example.startedspringbootaplication;

import com.example.startedspringbootaplication.model.*;
import com.example.startedspringbootaplication.model.enums.Role;
import com.example.startedspringbootaplication.model.enums.StudentFormat;
import com.example.startedspringbootaplication.model.user.Users;
import com.example.startedspringbootaplication.repository.CompanyRepository;
import com.example.startedspringbootaplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;

@SpringBootApplication
@RestController
@RequiredArgsConstructor
public class StartedSpringBootAplicationApplication {
    private final CompanyRepository repository;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public static void main(String[] args) {
        SpringApplication.run(StartedSpringBootAplicationApplication.class, args);
    }

    @PostConstruct
    public void init() {
        Users user = new Users("company", encoder.encode("company"), Role.ADMIN);
        Company company = new Company("IT.KG", user.getEmail(), user.getPassword());
        Course course = new Course("course", "9 month");
        course.setCompany(company);
        Users user1 = new Users("teacher", encoder.encode("teacher"), Role.TEACHER);
        Teacher teacher = new Teacher(user1.getEmail(), user1.getPassword(), user1.getRole());
        teacher.setCourse(course);
        course.setTeacher(teacher);
        company.setCourses(List.of(course));

        Group group = new Group("group");

        group.setCompany(company);
        group.setCourses(List.of(course));
        Users user2 = new Users("student", encoder.encode("student"), Role.STUDENT);
        Student student = new Student(user2.getEmail(), user2.getPassword(), user2.getRole(), StudentFormat.OFFLINE);
        student.setGroup(group);
        group.setStudents(List.of(student));
        company.setGroups(List.of(group));
        repository.save(company);
        userRepository.saveAll(List.of(user, user1, user2));
    }
}