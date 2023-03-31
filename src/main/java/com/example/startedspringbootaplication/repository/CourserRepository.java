package com.example.startedspringbootaplication.repository;

import com.example.startedspringbootaplication.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourserRepository extends JpaRepository<Course, Long> {
}