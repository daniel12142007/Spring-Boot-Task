package com.example.startedspringbootaplication.api.CourseApi;

import com.example.startedspringbootaplication.dto.request.CourseRequest;
import com.example.startedspringbootaplication.dto.response.CourserResponse;
import com.example.startedspringbootaplication.service.ServiceVersionCourse;
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

    private final ServiceVersionCourse serviceVersionCourse;

    @PostMapping("/save/course/{companyId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "save course ", description = " course can only be saved to the admin")
    public ResponseEntity<String> save(@RequestBody CourseRequest request, @PathVariable Long companyId) {
        serviceVersionCourse.saveCourse(request,companyId);
        return ResponseEntity.ok().body("user with name:" + request.getEmail() + " successfully save");
    }

    @GetMapping("get/course/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "get by id course ", description = " course can be taken by ID for admin ")
    public CourserResponse getbyid(@PathVariable Long id) {
        return serviceVersionCourse.getbyid(id);
    }

    @GetMapping("find/all")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "find all teacher ", description = " course can only find all id to the admin ")
    public List<CourserResponse> findAll() {
        return serviceVersionCourse.findAll();
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "save teacher ", description = " course can only be delete to the admin")
    public ResponseEntity<String> save(@PathVariable Long id) {
        serviceVersionCourse.delete(id);
        return ResponseEntity.ok().body("delete");
    }
}