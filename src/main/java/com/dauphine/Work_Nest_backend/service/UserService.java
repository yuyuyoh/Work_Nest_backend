package com.dauphine.Work_Nest_backend.service;

import com.dauphine.Work_Nest_backend.dto.EmployerRequest;
import com.dauphine.Work_Nest_backend.dto.EmployerResponse;
import com.dauphine.Work_Nest_backend.dto.JobseekerRequest;
import com.dauphine.Work_Nest_backend.dto.JobseekerResponse;
import com.dauphine.Work_Nest_backend.dto.LoginResponse;
import com.dauphine.Work_Nest_backend.entity.Jobseeker;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    // Uploads
    String uploadResume(Integer jobseekerId, MultipartFile file);
    String uploadPhoto(Integer jobseekerId, MultipartFile file);

    // Jobseeker
    Jobseeker getJobseekerById(Integer id);
    /**
     * Crée un candidat, enregistre ses expériences et compétences si présentes.
     */
    JobseekerResponse createJobseeker(JobseekerRequest jobseekerRequest);
    JobseekerResponse updateJobseekerProfile(Integer id, JobseekerRequest updates);
    // Ajoute cette méthode dans UserService.java
    JobseekerResponse getJobseekerResponseById(Integer id);

    // Employer
    EmployerResponse createEmployer(EmployerRequest employerRequest);

    // Authentification
    LoginResponse authenticate(String email, String password);
}
