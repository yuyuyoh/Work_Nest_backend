package com.dauphine.Work_Nest_backend.repository;

import com.dauphine.Work_Nest_backend.entity.Application;
import com.dauphine.Work_Nest_backend.enums.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    List<Application> findByJobId(Integer jobId);
    List<Application> findByJobseekerId(Integer jobseekerId);
    List<Application> findByStatus(ApplicationStatus status);

    boolean existsByJobIdAndJobseekerId(Integer jobId, Integer jobseekerId);
}