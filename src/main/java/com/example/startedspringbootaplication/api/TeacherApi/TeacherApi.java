package com.example.startedspringbootaplication.api.TeacherApi;

import com.example.startedspringbootaplication.dto.request.TeacherRequest;
import com.example.startedspringbootaplication.dto.response.TeacherResponse;
import com.example.startedspringbootaplication.service.ServiceVersionTeacher;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/teacher")
@RequiredArgsConstructor
public class TeacherApi {
    private final ServiceVersionTeacher serviceVersionTeacher;


    @PostMapping("/save/teacher/{courseId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "save teacher ", description = " teacher can only be saved to the admin")
    public ResponseEntity<String> save(@RequestBody TeacherRequest request, @PathVariable Long courseId) {
        serviceVersionTeacher.saveTeacher(request, courseId);
        return ResponseEntity.ok().body("user with name:" + request.getEmail() + " successfully save");
    }

    @GetMapping("get/teacher/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
    @Operation(summary = "get by id teacher ", description = " teacher can only be get by id to the admin and teacher")
    public TeacherResponse getbyid(@PathVariable Long id) {
        return serviceVersionTeacher.getById(id);
    }

    @GetMapping("find/all")
    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
    @Operation(summary = "find all teacher ", description = " teacher can be taken by ID for teacher and admin")
    public List<TeacherResponse> findAll() {
        return serviceVersionTeacher.findAll();
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "save teacher ", description = " teacher can only be delete to the admin")
    public ResponseEntity<String> save(@PathVariable Long id) {
        serviceVersionTeacher.delete(id);
        return ResponseEntity.ok().body("delete");
    }
}