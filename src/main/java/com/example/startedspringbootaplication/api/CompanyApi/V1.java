package com.example.startedspringbootaplication.api.CompanyApi;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/company")
public class V1 {
    @GetMapping("/hello")
    @Operation(summary = "Hello ", description = " I'm daniel")
    public String hello() {
        return "Hello World";
    }
}