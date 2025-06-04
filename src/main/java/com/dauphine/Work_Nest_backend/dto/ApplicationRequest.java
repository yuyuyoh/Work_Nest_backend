package com.dauphine.Work_Nest_backend.dto;

import com.dauphine.Work_Nest_backend.enums.ApplicationStatus;
import jakarta.validation.constraints.NotNull;

public class ApplicationRequest {

    @NotNull(message = "Job ID is mandatory")
    private Integer jobId;

    @NotNull(message = "Jobseeker ID is mandatory")
    private Integer jobseekerId;

    @NotNull(message = "Status is mandatory")
    private ApplicationStatus status;

    // Getters and Setters
    public Integer getJobId() { return jobId; }
    public void setJobId(Integer jobId) { this.jobId = jobId; }

    public Integer getJobseekerId() { return jobseekerId; }
    public void setJobseekerId(Integer jobseekerId) { this.jobseekerId = jobseekerId; }

    public ApplicationStatus getStatus() { return status; }
    public void setStatus(ApplicationStatus status) { this.status = status; }
}
