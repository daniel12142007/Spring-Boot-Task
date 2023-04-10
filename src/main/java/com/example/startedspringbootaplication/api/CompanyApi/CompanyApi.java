package com.example.startedspringbootaplication.api.CompanyApi;

import com.example.startedspringbootaplication.dto.response.CompanyResponse;
import com.example.startedspringbootaplication.service.ServiceVersionCompany;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/company")
@RequiredArgsConstructor
public class CompanyApi {
    private final ServiceVersionCompany company;

    @GetMapping("/find/All/company")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "get all company ", description = " company can only be get all to the admin")
    public List<CompanyResponse> findAll() {
        return company.findAll();
    }

    @GetMapping("/find/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "get by id company", description = " company can only be get by id to the admin")
    public CompanyResponse getById(@PathVariable Long id) {
        return company.getbyid(id);
    }
}