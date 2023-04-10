package com.example.startedspringbootaplication.api.StudentApi;

import com.example.startedspringbootaplication.dto.auth.AuthRequest;
import com.example.startedspringbootaplication.dto.auth.AuthResponse;
import com.example.startedspringbootaplication.dto.request.CompanyRequest;
import com.example.startedspringbootaplication.dto.request.StudentRequest;
import com.example.startedspringbootaplication.service.ServiceVersionCompany;
import com.example.startedspringbootaplication.service.ServiceVersionStudent;
import com.example.startedspringbootaplication.service.auth.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;

@RestController
@RequestMapping("api/v1/student")
@RequiredArgsConstructor
public class V4 {
    private final ServiceVersionStudent company;
    private final AuthService authService;

    @PostMapping("/login")
    @PermitAll
    public AuthResponse authenticated(@RequestBody AuthRequest requestBody) {
        return authService.authenticate(requestBody);
    }

    @PostMapping("/save/student")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "save student ", description = " student can only be saved to the admin")
    public ResponseEntity<String> save(@RequestBody StudentRequest request) {
        company.saveStudent(request);
        return ResponseEntity.ok().body("user with name:" + request.getEmail() + " successfully save");
    }
}
