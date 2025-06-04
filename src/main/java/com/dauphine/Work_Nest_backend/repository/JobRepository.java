package com.dauphine.Work_Nest_backend.repository;

import com.dauphine.Work_Nest_backend.entity.Job;
import com.dauphine.Work_Nest_backend.enums.JobType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface JobRepository extends JpaRepository<Job, Integer> {
    List<Job> findByLocation(String location);
    List<Job> findByType(JobType type);
    List<Job> findByEmployerId(Integer employerId);

    @Query("SELECT j FROM Job j WHERE j.salaryMin >= :minSalary AND j.salaryMax <= :maxSalary")
    List<Job> findBySalaryRange(@Param("minSalary") float minSalary,
                                @Param("maxSalary") float maxSalary);

    @Query("SELECT j FROM Job j WHERE LOWER(j.title) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Job> searchByKeyword(@Param("keyword") String keyword);

    // Nouvelle méthode pour les salaires minimum
    List<Job> findBySalaryMinGreaterThanEqual(float minSalary);

    @Query("SELECT j FROM Job j ORDER BY j.postedAt DESC")
    List<Job> findAllOrderByPostedAtDesc();

}