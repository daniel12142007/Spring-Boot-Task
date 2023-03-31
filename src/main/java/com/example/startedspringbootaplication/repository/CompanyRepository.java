package com.example.startedspringbootaplication.repository;

import com.example.startedspringbootaplication.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByEmail(String name);

    @Query("select case when count(u)>0 then true else false end from Company u where u.email like :email")
    boolean existsByEmail(@Param(value = "email") String email);
}