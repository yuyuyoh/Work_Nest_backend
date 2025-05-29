package com.dauphine.Work_Nest_backend.service;

import com.dauphine.Work_Nest_backend.entity.Employer;
import com.dauphine.Work_Nest_backend.entity.Jobseeker;
import com.dauphine.Work_Nest_backend.entity.Login;

public interface UserService {
    Login authenticate(String email, String password);
    Jobseeker createJobseeker(Jobseeker jobseeker);
    Employer createEmployer(Employer employer);
    Jobseeker updateJobseekerProfile(Integer id, Jobseeker updates);
}
