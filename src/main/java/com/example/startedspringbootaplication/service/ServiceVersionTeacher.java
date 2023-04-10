package com.example.startedspringbootaplication.service;

import com.example.startedspringbootaplication.dto.request.TeacherRequest;
import com.example.startedspringbootaplication.dto.response.TeacherResponse;
import com.example.startedspringbootaplication.model.Teacher;
import com.example.startedspringbootaplication.model.role.Role;
import com.example.startedspringbootaplication.model.Users;
import com.example.startedspringbootaplication.repository.TeacherRepository;
import com.example.startedspringbootaplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceVersionTeacher {
    private final TeacherRepository companyRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;

    public ResponseEntity<String> saveTeacher(TeacherRequest request) {
        if (companyRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("this email is already have in!");
        }
        Users user = new Users();
        user.setEmail(request.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        user.setRole(Role.TEACHER);
        Teacher company = new Teacher(user.getEmail(),user.getPassword(), user.getRole());
        companyRepository.save(company);
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    public TeacherResponse getbyid(Long id) {
        Teacher groupResponse = companyRepository.findById(id).get();
        if (groupResponse.getId() == null) {
            return null;
        }
        TeacherResponse response = new TeacherResponse();
        response.setEmail(groupResponse.getEmail());
        response.setCourseId(String.valueOf(groupResponse.getId()));
        return response;
    }


}
