package com.example.startedspringbootaplication.service;

import com.example.startedspringbootaplication.dto.request.TeacherRequest;
import com.example.startedspringbootaplication.dto.response.TeacherResponse;
import com.example.startedspringbootaplication.model.Teacher;
import com.example.startedspringbootaplication.model.enums.Role;
import com.example.startedspringbootaplication.model.user.Users;
import com.example.startedspringbootaplication.repository.CourseRepository;
import com.example.startedspringbootaplication.repository.TeacherRepository;
import com.example.startedspringbootaplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository companyRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final CourseRepository courseRepository;

    public ResponseEntity<String> saveTeacher(TeacherRequest request, Long courseId) {
        if (companyRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("-----------------------------------------------------this email is already have in!-----------------------------------------------------");
        }
        Users user = new Users();
        user.setEmail(request.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        user.setRole(Role.TEACHER);
        Teacher teacher = new Teacher(user.getEmail(), user.getPassword(), user.getRole());
        teacher.setCourse(courseRepository.getById(courseId));
        companyRepository.save(teacher);
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    public TeacherResponse getByIdTeacher(Long id) {
        try {
            Teacher teacher = companyRepository.findById(id).get();
            if (teacher.getId() == null) {
                return null;
            }
            TeacherResponse response = new TeacherResponse();
            response.setEmail(teacher.getEmail());
            response.setCourseId(String.valueOf(teacher.getId()));
            return response;
        } catch (RuntimeException e) {
            throw new RuntimeException("-----------------------there is no such:\" + id + \" please enter an ID that exists-----------------------");
        }
    }

    public List<TeacherResponse> findAllTeachers() {
        List<Teacher> list = companyRepository.findAll();
        List<TeacherResponse> getList = new ArrayList<>();
        for (Teacher teacher : list) {
            getList.add(getByIdTeacher(teacher.getId()));
        }
        return getList;
    }

    public ResponseEntity<String> deleteByIdTeacher(Long id) {
        try {
        companyRepository.deleteById(id);
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
        }catch (RuntimeException e){
            throw new RuntimeException("-----------------------------------------------------not fount id-----------------------------------------------------");
        }
    }
}
