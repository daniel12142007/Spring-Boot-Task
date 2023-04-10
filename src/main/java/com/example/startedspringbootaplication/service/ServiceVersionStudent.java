package com.example.startedspringbootaplication.service;

import com.example.startedspringbootaplication.dto.request.StudentRequest;
import com.example.startedspringbootaplication.dto.response.StudentResponse;
import com.example.startedspringbootaplication.model.Student;
import com.example.startedspringbootaplication.model.Users;
import com.example.startedspringbootaplication.model.role.Role;
import com.example.startedspringbootaplication.model.role.StudentFormat;
import com.example.startedspringbootaplication.repository.StudentRepository;
import com.example.startedspringbootaplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceVersionStudent {
    private final StudentRepository companyRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;

    public ResponseEntity<String> saveStudent(StudentRequest request) {
        if (companyRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("this email is already have in!");
        }
        Users user = new Users();
        user.setEmail(request.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        user.setRole(Role.STUDENT);
        Student company = new Student(user.getEmail(), bCryptPasswordEncoder.encode(user.getPassword()), user.getRole(), StudentFormat.OFFLINE);
        companyRepository.save(company);
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    public StudentResponse getbyid(Long id) {
        Student groupResponse = companyRepository.findById(id).get();
        if (groupResponse.getId() == null) {
            return null;
        }
        StudentResponse response = new StudentResponse();
        response.setEmail(groupResponse.getEmail());
        response.setGroupsId(String.valueOf(groupResponse.getId()));
        return response;
    }
}
