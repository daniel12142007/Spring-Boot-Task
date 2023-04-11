package com.example.startedspringbootaplication.repository;

import com.example.startedspringbootaplication.model.Groups;
import com.example.startedspringbootaplication.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupsRepository extends JpaRepository<Groups, Long> {

}