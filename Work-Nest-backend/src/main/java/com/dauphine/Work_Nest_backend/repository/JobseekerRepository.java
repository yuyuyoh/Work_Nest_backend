package com.dauphine.Work_Nest_backend.repository;

import com.dauphine.Work_Nest_backend.entity.Jobseeker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobseekerRepository extends JpaRepository<Jobseeker, Integer> {
    Optional<Jobseeker> findByLoginId(Integer loginId);
}