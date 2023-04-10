package com.example.startedspringbootaplication.api.CompanyApi;

import com.example.startedspringbootaplication.dto.auth.AuthRequest;
import com.example.startedspringbootaplication.dto.auth.AuthResponse;
import com.example.startedspringbootaplication.dto.request.CompanyRequest;
import com.example.startedspringbootaplication.service.ServiceVersionCompany;
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
@RequestMapping("api/v1/company")
@RequiredArgsConstructor
public class V1 {
    private final ServiceVersionCompany company;
    private final AuthService authService;

    @PostMapping("/login")
    @PermitAll
    public AuthResponse authenticated(@RequestBody AuthRequest requestBody) {
        return authService.authenticate(requestBody);
    }

    @PostMapping("/save/company")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "save company ", description = " company can only be saved to the admin")
    public ResponseEntity<String> save(@RequestBody CompanyRequest request) {
        company.saveCompany(request);
        return ResponseEntity.ok().body("user with name:" + request.getEmail() + " successfully save");
    }
}