package com.example.startedspringbootaplication.repository;

import com.example.startedspringbootaplication.model.Groups;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupsRepository extends JpaRepository<Groups, Long> {
}