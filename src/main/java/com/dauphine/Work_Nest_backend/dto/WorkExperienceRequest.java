package com.dauphine.Work_Nest_backend.dto;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

public class WorkExperienceRequest {
    @NotBlank(message = "Job title is mandatory")
    private String title;

    private String company;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;

    // Getters and Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
