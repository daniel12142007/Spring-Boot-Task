package com.example.startedspringbootaplication.api;

import com.example.startedspringbootaplication.dto.request.StudentRequest;
import com.example.startedspringbootaplication.dto.response.StudentResponse;
import com.example.startedspringbootaplication.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/student")
@RequiredArgsConstructor
public class StudentApi {
    private final StudentService studentService;

    @PostMapping("/save/student/{Groupid}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "save student ", description = " student can only be saved to the admin")
    public ResponseEntity<String> save(@PathVariable Long Groupid, @RequestBody StudentRequest request) {
        studentService.saveStudent(request, Groupid);
        return ResponseEntity.ok().body("user with name:" + request.getEmail() + " successfully save");
    }

    @GetMapping("get/student/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','STUDENT')")
    @Operation(summary = "get by id student ", description = " student can only be get by id to the admin and teacher")
    public StudentResponse getById(@PathVariable Long id) {
        return studentService.getByIdStudent(id);
    }

    @GetMapping("find/all")
    @PreAuthorize("hasAnyAuthority('ADMIN','STUDENT')")
    @Operation(summary = "find all student ", description = " student can be taken by ID for student and admin")
    public List<StudentResponse> findAll() {
        return studentService.findAllStudents();
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "save student ", description = " student can only be deleteByIdCourse to the admin")
    public ResponseEntity<String> save(@PathVariable Long id) {
        studentService.deleteByIdStudent(id);
        return ResponseEntity.ok().body("deleteByIdCourse");
    }
}