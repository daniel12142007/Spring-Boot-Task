package com.example.startedspringbootaplication.api;

import com.example.startedspringbootaplication.dto.request.CompanyRequest;
import com.example.startedspringbootaplication.dto.response.CompanyResponse;
import com.example.startedspringbootaplication.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/company")
@RequiredArgsConstructor
public class CompanyApi {
    private final CompanyService company;

    @PostMapping("/save/company")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "save company ", description = " company can only be saved to the admin")
    public ResponseEntity<String> save(@RequestBody CompanyRequest request) {
        company.saveCompany(request);
        return ResponseEntity.ok().body("user with name:" + request.getCompanyName() + " successfully save");
    }

    @GetMapping("/find/All/company")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "get all company ", description = " company can only be get all to the admin")
    public List<CompanyResponse> findAll() {
        return company.findAllCompanies();
    }

    @GetMapping("/find/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "get by id company", description = " company can only be get by id to the admin")
    public CompanyResponse getById(@PathVariable Long id) {
        return company.getByIdCompany(id);
    }
}