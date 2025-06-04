package com.dauphine.Work_Nest_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class JobRequest {
    @NotBlank(message = "Title is mandatory")
    private String title;

    private String description;
    private String location;

    @PositiveOrZero(message = "Salary must be positive")
    private float salaryMin;

    @PositiveOrZero(message = "Salary must be positive")
    private float salaryMax;

    @NotBlank(message = "Job type is mandatory")
    private String type;

    @NotNull(message = "Employer ID is mandatory")
    private Integer employerId;
    @PositiveOrZero(message = "Experience must be positive")
    private int experience;

    @PositiveOrZero(message = "Vacancies must be positive")
    private int vacancies;

    // Getters and Setters
    public int getExperience() { return experience; }
    public void setExperience(int experience) { this.experience = experience; }

    public int getVacancies() { return vacancies; }
    public void setVacancies(int vacancies) { this.vacancies = vacancies; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public float getSalaryMin() { return salaryMin; }
    public void setSalaryMin(float salaryMin) { this.salaryMin = salaryMin; }

    public float getSalaryMax() { return salaryMax; }
    public void setSalaryMax(float salaryMax) { this.salaryMax = salaryMax; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Integer getEmployerId() { return employerId; }
    public void setEmployerId(Integer employerId) { this.employerId = employerId; }
}