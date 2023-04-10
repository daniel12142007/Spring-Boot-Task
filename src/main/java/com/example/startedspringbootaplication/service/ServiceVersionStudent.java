package com.example.startedspringbootaplication.service;

import com.example.startedspringbootaplication.dto.request.StudentRequest;
import com.example.startedspringbootaplication.dto.response.StudentResponse;
import com.example.startedspringbootaplication.model.Student;
import com.example.startedspringbootaplication.model.Users;
import com.example.startedspringbootaplication.model.enums.Role;
import com.example.startedspringbootaplication.model.enums.StudentFormat;
import com.example.startedspringbootaplication.repository.GroupsRepository;
import com.example.startedspringbootaplication.repository.StudentRepository;
import com.example.startedspringbootaplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceVersionStudent {
    private final StudentRepository companyRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final GroupsRepository repository;

    public ResponseEntity<String> saveStudent(StudentRequest request, Long id) {
        if (companyRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("this email is already have in!");
        }
        Users user = new Users();
        user.setEmail(request.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        user.setRole(Role.STUDENT);
        Student company = new Student(user.getEmail(), user.getPassword(), user.getRole(), StudentFormat.OFFLINE);
        company.setGroup(repository.getById(id));
        companyRepository.save(company);
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    public StudentResponse getbyid(Long id) {
        try {
            Student groupResponse = companyRepository.findById(id).get();
            if (groupResponse.getId() == null) {
                return null;
            }
            StudentResponse response = new StudentResponse();
            response.setEmail(groupResponse.getEmail());
            response.setGroupsId(String.valueOf(groupResponse.getId()));
            response.setFormat(String.valueOf(groupResponse.getStudentFormat()));
            return response;
        } catch (RuntimeException e) {
            throw new RuntimeException("-----------------------there is no such:\" + id + \" please enter an ID that exists-----------------------");
        }
    }

    public List<StudentResponse> findAll() {
        List<Student> list = companyRepository.findAll();
        List<StudentResponse> getList = new ArrayList<>();
        for (Student sudent : list) {
            getList.add(getbyid(sudent.getId()));
        }
        return getList;
    }

    public ResponseEntity<String> delete(Long id) {
        try {
            companyRepository.deleteById(id);
            userRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            throw new RuntimeException("-----------------------------------------------------not fount id-----------------------------------------------------");
        }
    }
}
