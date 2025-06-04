package com.dauphine.Work_Nest_backend.repository;

import com.dauphine.Work_Nest_backend.entity.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkExperienceRepository extends JpaRepository<WorkExperience, Integer> {
    List<WorkExperience> findByJobseekerId(Integer jobseekerId);
}