package com.example.startedspringbootaplication.service;

import com.example.startedspringbootaplication.dto.request.CourseRequest;
import com.example.startedspringbootaplication.dto.response.CourserResponse;
import com.example.startedspringbootaplication.model.Course;
import com.example.startedspringbootaplication.repository.CompanyRepository;
import com.example.startedspringbootaplication.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final CompanyRepository companyRepository;

    public ResponseEntity<String> saveCourse(CourseRequest request, Long id) {
        Course course = new Course();
        course.setCompany(companyRepository.getById(id));
        course.setCourseName(request.getCourseName());
        course.setDuration(request.getDuration());
        courseRepository.save(course);
        return ResponseEntity.ok().build();
    }

    public CourserResponse getByIdCourse(Long id) {
        try {
            Course course = courseRepository.findById(id).get();
            if (course.getId() == null) {
                return null;
            }
            CourserResponse response = new CourserResponse();
            response.setCourseName(course.getCourseName());
            response.setDuration(course.getDuration());
            response.setCompanyId(String.valueOf(course.getId()));
            return response;
        } catch (RuntimeException e) {
            throw new RuntimeException("-----------------------there is no such: " + id + " please enter an ID that exists-----------------------");
        }
    }

    public List<CourserResponse> findAllCourses() {
        List<Course> list = courseRepository.findAll();
        List<CourserResponse> getList = new ArrayList<>();
        for (Course course : list) {
            getList.add(getByIdCourse(course.getId()));
        }
        return getList;
    }

    public ResponseEntity<String> deleteByIdCourse(Long id) {
        try {
            companyRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            throw new RuntimeException("-----------------------------------------------------not fount id-----------------------------------------------------");
        }
    }
}