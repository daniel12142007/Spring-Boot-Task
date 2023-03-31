package com.example.startedspringbootaplication.repository;

import com.example.startedspringbootaplication.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}