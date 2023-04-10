package com.example.startedspringbootaplication.api.StudentApi;

import com.example.startedspringbootaplication.dto.request.StudentRequest;
import com.example.startedspringbootaplication.dto.response.StudentResponse;
import com.example.startedspringbootaplication.service.ServiceVersionStudent;
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
    private final ServiceVersionStudent company;

    @PostMapping("/save/student/{Groupid}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "save student ", description = " student can only be saved to the admin")
    public ResponseEntity<String> save(@PathVariable Long Groupid, @RequestBody StudentRequest request) {
        company.saveStudent(request, Groupid);
        return ResponseEntity.ok().body("user with name:" + request.getEmail() + " successfully save");
    }

    @GetMapping("get/student/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','STUDENT')")
    @Operation(summary = "get by id student ", description = " student can only be get by id to the admin and teacher")
    public StudentResponse getbyid(@PathVariable Long id) {
        return company.getbyid(id);
    }

    @GetMapping("find/all")
    @PreAuthorize("hasAnyAuthority('ADMIN','STUDENT')")
    @Operation(summary = "find all student ", description = " student can be taken by ID for student and admin")
    public List<StudentResponse> findAll() {
        return company.findAll();
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "save student ", description = " student can only be delete to the admin")
    public ResponseEntity<String> save(@PathVariable Long id) {
        company.delete(id);
        return ResponseEntity.ok().body("delete");
    }
}