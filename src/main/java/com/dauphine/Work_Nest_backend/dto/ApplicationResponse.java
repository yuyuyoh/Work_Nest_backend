package com.dauphine.Work_Nest_backend.dto;
import java.time.LocalDateTime;

public class ApplicationResponse {
    private Integer id;
    private String jobTitle;
    private Integer jobId;

    // Infos du candidat
    private Integer jobseekerId;
    private String jobseekerFirstName;
    private String jobseekerLastName;
    private String jobseekerEmail;
    private String jobseekerCV;
    private String jobseekerPhone;

    private String status;
    private LocalDateTime appliedAt;

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getJobId() { return jobId; }
    public void setJobId(Integer jobId) { this.jobId = jobId; }

    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }

    public Integer getJobseekerId() { return jobseekerId; }
    public void setJobseekerId(Integer jobseekerId) { this.jobseekerId = jobseekerId; }

    public String getJobseekerFirstName() { return jobseekerFirstName; }
    public void setJobseekerFirstName(String jobseekerFirstName) { this.jobseekerFirstName = jobseekerFirstName; }

    public String getJobseekerLastName() { return jobseekerLastName; }
    public void setJobseekerLastName(String jobseekerLastName) { this.jobseekerLastName = jobseekerLastName; }

    public String getJobseekerEmail() { return jobseekerEmail; }
    public void setJobseekerEmail(String jobseekerEmail) { this.jobseekerEmail = jobseekerEmail; }

    public String getJobseekerCV() { return jobseekerCV; }
    public void setJobseekerCV(String jobseekerCV) { this.jobseekerCV = jobseekerCV; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getAppliedAt() { return appliedAt; }
    public void setAppliedAt(LocalDateTime appliedAt) { this.appliedAt = appliedAt; }

    public String getJobseekerPhone() { return jobseekerPhone; }

    public void setJobseekerPhone(String jobseekerPhone) {
        this.jobseekerPhone = jobseekerPhone;
    }
}
