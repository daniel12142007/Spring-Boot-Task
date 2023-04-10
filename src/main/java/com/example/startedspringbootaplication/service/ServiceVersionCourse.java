package com.example.startedspringbootaplication.service;

import com.example.startedspringbootaplication.dto.request.CourseRequest;
import com.example.startedspringbootaplication.dto.response.CourserResponse;
import com.example.startedspringbootaplication.model.Course;
import com.example.startedspringbootaplication.repository.CompanyRepository;
import com.example.startedspringbootaplication.repository.CourserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceVersionCourse {
    private final CourserRepository courserRepository;
    private final CompanyRepository companyRepository;

    public ResponseEntity<String> saveCourse(CourseRequest request, Long id) {
        Course course = new Course();
        course.setCompany(companyRepository.getById(id));
        course.setEmail(request.getEmail());
        courserRepository.save(course);
        return ResponseEntity.ok().build();
    }

    public CourserResponse getById(Long id) {
        try {
            Course groupResponse = courserRepository.findById(id).get();
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
        List<Course> list = courserRepository.findAll();
        List<CourserResponse> getList = new ArrayList<>();
        for (Course course : list) {
            getList.add(getById(course.getId()));
        }
        return getList;
    }

    public ResponseEntity<String> delete(Long id) {
        try {
            companyRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            throw new RuntimeException("-----------------------------------------------------not fount id-----------------------------------------------------");
        }
    }
}