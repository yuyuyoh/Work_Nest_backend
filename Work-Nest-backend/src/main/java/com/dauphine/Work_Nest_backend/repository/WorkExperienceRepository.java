package com.dauphine.Work_Nest_backend.repository;

import com.dauphine.Work_Nest_backend.entity.WorkExperiences;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkExperienceRepository extends JpaRepository<WorkExperiences, Integer> {
    List<WorkExperiences> findByJobseekerId(Integer jobseekerId);
}