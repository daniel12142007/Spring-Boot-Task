package com.example.startedspringbootaplication.service;

import com.example.startedspringbootaplication.dto.request.CompanyRequest;
import com.example.startedspringbootaplication.model.Company;
import com.example.startedspringbootaplication.model.role.Role;
import com.example.startedspringbootaplication.model.users.Users;
import com.example.startedspringbootaplication.repository.CompanyRepository;
import com.example.startedspringbootaplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceVersionCompany {
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;

    public ResponseEntity<String> saveCompany(CompanyRequest request) {
        if (companyRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("this email is already have in!");
        }
        Users user = new Users();
        user.setEmail(request.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        user.setRole(Role.ADMIN);
        Company company = new Company(user.getEmail(), bCryptPasswordEncoder.encode(user.getPassword()), user.getRole());
        companyRepository.save(company);
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }
}