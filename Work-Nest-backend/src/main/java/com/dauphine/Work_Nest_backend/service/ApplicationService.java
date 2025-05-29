package com.dauphine.Work_Nest_backend.service;

import com.dauphine.Work_Nest_backend.entity.Application;

import java.util.List;

public interface ApplicationService {
    Application applyToJob(Application application);
    List<Application> getApplicationsByJobseeker(Integer jobseekerId);
    void updateApplicationStatus(Integer id, String status);
}