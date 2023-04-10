package com.example.startedspringbootaplication.service;

import com.example.startedspringbootaplication.dto.response.CompanyResponse;
import com.example.startedspringbootaplication.model.Company;
import com.example.startedspringbootaplication.repository.CompanyRepository;
import com.example.startedspringbootaplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceVersionCompany {
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;

//    public ResponseEntity<String> saveCompany(CompanyRequest request) {
//        if (companyRepository.existsByEmail(request.getEmail())) {
//            throw new RuntimeException("this email is already have in!");
//        }
//        Users user = new Users();
//        user.setEmail(request.getEmail());
//        user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
//        user.setRole(Role.ADMIN);
//        Company company = new Company(user.getEmail(),user.getPassword(), user.getRole());
//        companyRepository.save(company);
//        userRepository.save(user);
//        return ResponseEntity.ok().build();
//    }


    public CompanyResponse getbyid(Long id) {
        Company groupResponse = companyRepository.findById(id).get();
        if (groupResponse.getId() == null) {
            return null;
        }
        CompanyResponse response = new CompanyResponse();
        response.setEmail(groupResponse.getEmail());
        return response;
    }

    public List<CompanyResponse> findAll() {
        List<Company> list = companyRepository.findAll();
        List<CompanyResponse> getList = new ArrayList<>();
        for (Company sudent : list) {
            getList.add(getbyid(sudent.getId()));
        }
        return getList;
    }


}