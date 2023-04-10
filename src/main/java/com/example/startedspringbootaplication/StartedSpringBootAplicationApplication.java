package com.example.startedspringbootaplication;

import com.example.startedspringbootaplication.model.*;
import com.example.startedspringbootaplication.model.role.Role;
import com.example.startedspringbootaplication.model.users.Users;
import com.example.startedspringbootaplication.repository.CompanyRepository;
import com.example.startedspringbootaplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

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
        Company company = new Company("company", encoder.encode("company"), Role.ADMIN);
        Users user = new Users(company.getEmail(), encoder.encode(company.getPassword()), company.getRole());
        Course course = new Course("course");
        course.setCompany(company);
        Teacher teacher = new Teacher("teacher", encoder.encode("teacher"), Role.TEACHER);
        Users user1 = new Users(teacher.getEmail(), encoder.encode(teacher.getPassword()), teacher.getRole());
        teacher.setCourse(course);
        course.setTeacher(teacher);
        company.setCourses(List.of(course));

        Groups group = new Groups("group");

        group.setCompany(company);
        group.setCourses(List.of(course));
        Student student = new Student("student", encoder.encode("student"), Role.STUDENT);
        Users user2 = new Users(student.getEmail(), encoder.encode(student.getPassword()), student.getRole());
        student.setGroup(group);
        group.setStudents(List.of(student));
        company.setGroups(List.of(group));
        repository.save(company);
        userRepository.saveAll(List.of(user, user1, user2));
    }
}