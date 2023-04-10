package com.example.startedspringbootaplication.api.StudentApi;

import com.example.startedspringbootaplication.dto.request.StudentRequest;
import com.example.startedspringbootaplication.dto.response.StudentResponse;
import com.example.startedspringbootaplication.service.ServiceVersionStudent;
import com.example.startedspringbootaplication.service.auth.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/student")
@RequiredArgsConstructor
public class V4 {
    private final ServiceVersionStudent company;
    private final AuthService authService;
    @PostMapping("/save/student")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "save student ", description = " student can only be saved to the admin")
    public ResponseEntity<String> save(@RequestBody StudentRequest request) {
        company.saveStudent(request);
        return ResponseEntity.ok().body("user with name:" + request.getEmail() + " successfully save");
    }
    @GetMapping("get/student/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','STUDENT')")
    @Operation(summary = "get by id student ", description = " student can only be get by id to the admin and teacher")
    public StudentResponse getbyid(@PathVariable Long id) {
        return company.getbyid(id);
    }
}
