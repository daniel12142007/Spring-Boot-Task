package com.example.startedspringbootaplication.api.CourseApi;

import com.example.startedspringbootaplication.dto.request.CourseRequest;
import com.example.startedspringbootaplication.service.ServiceVersionCourse;
import com.example.startedspringbootaplication.service.auth.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/course")
@RequiredArgsConstructor
public class V2 {
    private final ServiceVersionCourse company;
    private final AuthService authService;

    @PostMapping("/save/course")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "save course ", description = " course can only be saved to the admin")
    public ResponseEntity<String> save(@RequestBody CourseRequest request) {
        company.saveCourse(request);
        return ResponseEntity.ok().body("user with name:" + request.getEmail() + " successfully save");
    }
}