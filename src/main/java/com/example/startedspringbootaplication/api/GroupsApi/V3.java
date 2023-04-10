package com.example.startedspringbootaplication.api.GroupsApi;

import com.example.startedspringbootaplication.dto.auth.AuthRequest;
import com.example.startedspringbootaplication.dto.auth.AuthResponse;
import com.example.startedspringbootaplication.dto.request.GroupsRequest;
import com.example.startedspringbootaplication.service.ServiceVersionGroup;
import com.example.startedspringbootaplication.service.auth.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;

@RestController
@RequestMapping("api/v1/group")
@RequiredArgsConstructor
public class V3 {
    private final ServiceVersionGroup company;
    private final AuthService authService;

    @PostMapping("/login")
    @PermitAll
    public AuthResponse authenticated(@RequestBody AuthRequest requestBody) {
        return authService.authenticate(requestBody);
    }
    @PostMapping("/save/group")
    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
    @Operation(summary = "save group ", description = " company can only be saved to the admin and teacher")
    public ResponseEntity<String> save(@RequestBody GroupsRequest request) {
        company.saveGroup(request);
        return ResponseEntity.ok().body("user with name:" + request.getEmail() + " successfully save");
    }
}
