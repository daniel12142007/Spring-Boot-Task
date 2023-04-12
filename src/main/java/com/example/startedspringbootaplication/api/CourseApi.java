package com.example.startedspringbootaplication.api;

import com.example.startedspringbootaplication.dto.request.CourseRequest;
import com.example.startedspringbootaplication.dto.response.CourserResponse;
import com.example.startedspringbootaplication.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/course")
@RequiredArgsConstructor
public class CourseApi {

    private final CourseService courseService;

    @PostMapping("/save/course/{companyId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "save course ", description = " course can only be saved to the admin")
    public ResponseEntity<String> save(@RequestBody CourseRequest request, @PathVariable Long companyId) {
        courseService.saveCourse(request,companyId);
        return ResponseEntity.ok().body("user with name:" + request.getCourseName() + " successfully save");
    }

    @GetMapping("get/course/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "get by id course ", description = " course can be taken by ID for admin ")
    public CourserResponse getbyid(@PathVariable Long id) {
        return courseService.getByIdCourse(id);
    }

    @GetMapping("find/all")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "find all teacher ", description = " course can only find all id to the admin ")
    public List<CourserResponse> findAll() {
        return courseService.findAllCourses();
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "save teacher ", description = " course can only be deleteByIdCourse to the admin")
    public ResponseEntity<String> save(@PathVariable Long id) {
        courseService.deleteByIdCourse(id);
        return ResponseEntity.ok().body("deleteByIdCourse");
    }
}