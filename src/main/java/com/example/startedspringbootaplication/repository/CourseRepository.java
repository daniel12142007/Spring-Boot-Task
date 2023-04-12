package com.example.startedspringbootaplication.repository;

import com.example.startedspringbootaplication.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}