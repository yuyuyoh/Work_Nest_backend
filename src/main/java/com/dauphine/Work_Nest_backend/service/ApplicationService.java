package com.dauphine.Work_Nest_backend.service;

import com.dauphine.Work_Nest_backend.dto.ApplicationRequest;
import com.dauphine.Work_Nest_backend.dto.ApplicationResponse;

import java.util.List;

public interface ApplicationService {
    ApplicationResponse applyToJob(ApplicationRequest applicationRequest);
    List<ApplicationResponse> getApplicationsByJobseeker(Integer jobseekerId);
    void updateApplicationStatus(Integer id, String status);
    List<ApplicationResponse> getApplicationsByJob(Integer jobId);
}
