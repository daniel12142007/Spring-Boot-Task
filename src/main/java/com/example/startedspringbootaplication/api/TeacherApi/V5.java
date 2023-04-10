package com.example.startedspringbootaplication.api.TeacherApi;

import com.example.startedspringbootaplication.dto.request.TeacherRequest;
import com.example.startedspringbootaplication.dto.response.TeacherResponse;
import com.example.startedspringbootaplication.service.ServiceVersionTeacher;
import com.example.startedspringbootaplication.service.auth.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/teacher")
@RequiredArgsConstructor
public class V5 {
    private final ServiceVersionTeacher company;
    private final AuthService authService;


    @PostMapping("/save/teacher")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "save teacher ", description = " teacher can only be saved to the admin")
    public ResponseEntity<String> save(@RequestBody TeacherRequest request) {
        company.saveTeacher(request);
        return ResponseEntity.ok().body("user with name:" + request.getEmail() + " successfully save");
    }

    @GetMapping("get/teacher/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
    @Operation(summary = "get by id teacher ", description = " teacher can only be get by id to the admin and teacher")
    public TeacherResponse getbyid(@PathVariable Long id) {
        return company.getbyid(id);
    }
}
