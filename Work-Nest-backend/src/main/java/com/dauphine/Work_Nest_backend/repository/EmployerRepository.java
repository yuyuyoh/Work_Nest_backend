package com.dauphine.Work_Nest_backend.repository;

import com.dauphine.Work_Nest_backend.entity.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployerRepository extends JpaRepository<Employer, Integer> {
    Optional<Employer> findByLoginId(Integer loginId);
}