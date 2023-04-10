package com.example.startedspringbootaplication.service;

import com.example.startedspringbootaplication.dto.request.CourseRequest;
import com.example.startedspringbootaplication.model.Course;
import com.example.startedspringbootaplication.repository.CourserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceVersionCourse {
    private final CourserRepository companyRepository;

    public ResponseEntity<String> saveCourse(CourseRequest request) {
        Course company = new Course();
        company.setEmail(request.getEmail());
        companyRepository.save(company);
        return ResponseEntity.ok().build();
    }
}