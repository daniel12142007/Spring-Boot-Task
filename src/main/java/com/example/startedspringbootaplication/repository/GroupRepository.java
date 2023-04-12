package com.example.startedspringbootaplication.repository;

import com.example.startedspringbootaplication.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}