package com.example.startedspringbootaplication.service;

import com.example.startedspringbootaplication.dto.request.CourseRequest;
import com.example.startedspringbootaplication.dto.response.CourserResponse;
import com.example.startedspringbootaplication.dto.response.StudentResponse;
import com.example.startedspringbootaplication.model.Course;
import com.example.startedspringbootaplication.model.Student;
import com.example.startedspringbootaplication.repository.CourserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public CourserResponse getbyid(Long id) {
        try {
            Course groupResponse = companyRepository.findById(id).get();
            if (groupResponse.getId() == null) {
                return null;
            }
            CourserResponse response = new CourserResponse();
            response.setEmail(groupResponse.getEmail());
            response.setCompanyId(String.valueOf(groupResponse.getId()));
            return response;
        } catch (RuntimeException e) {
            throw new RuntimeException("-----------------------there is no such: " + id + " please enter an ID that exists-----------------------");
        }
    }

    public List<CourserResponse> findAll() {
        List<Course> list = companyRepository.findAll();
        List<CourserResponse> getList = new ArrayList<>();
        for (Course sudent : list) {
            getList.add(getbyid(sudent.getId()));
        }
        return getList;
    }

    public ResponseEntity<String> delete(Long id) {
        companyRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}