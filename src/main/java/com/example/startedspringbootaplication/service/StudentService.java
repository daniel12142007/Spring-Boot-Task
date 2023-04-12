package com.example.startedspringbootaplication.service;

import com.example.startedspringbootaplication.dto.request.StudentRequest;
import com.example.startedspringbootaplication.dto.response.StudentResponse;
import com.example.startedspringbootaplication.model.Student;
import com.example.startedspringbootaplication.model.Users;
import com.example.startedspringbootaplication.model.enums.Role;
import com.example.startedspringbootaplication.model.enums.StudentFormat;
import com.example.startedspringbootaplication.repository.GroupRepository;
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
public class StudentService {
    private final StudentRepository companyRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final GroupRepository repository;

    public ResponseEntity<String> saveStudent(StudentRequest request, Long id) {
        if (companyRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("this email is already have in!");
        }
        Users user = new Users();
        user.setEmail(request.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        user.setRole(Role.STUDENT);
        Student student = new Student(user.getEmail(), user.getPassword(), user.getRole(), StudentFormat.OFFLINE);
        student.setGroup(repository.getById(id));
        companyRepository.save(student);
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    public StudentResponse getByIdStudent(Long id) {
        try {
            Student studentResponse = companyRepository.findById(id).get();
            if (studentResponse.getId() == null) {
                return null;
            }
            StudentResponse response = new StudentResponse();
            response.setEmail(studentResponse.getEmail());
            response.setGroupsId(String.valueOf(studentResponse.getId()));
            response.setFormat(String.valueOf(studentResponse.getStudentFormat()));
            return response;
        } catch (RuntimeException e) {
            throw new RuntimeException("-----------------------there is no such:\" + id + \" please enter an ID that exists-----------------------");
        }
    }

    public List<StudentResponse> findAllStudents() {
        List<Student> list = companyRepository.findAll();
        List<StudentResponse> getList = new ArrayList<>();
        for (Student student : list) {
            getList.add(getByIdStudent(student.getId()));
        }
        return getList;
    }

    public ResponseEntity<String> deleteByIdStudent(Long id) {
        try {
            companyRepository.deleteById(id);
            userRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            throw new RuntimeException("-----------------------------------------------------not fount id-----------------------------------------------------");
        }
    }
}
