package com.dauphine.Work_Nest_backend.dto;
import java.time.LocalDateTime;

public class JobResponse {
    private Integer id;
    private String title;
    private String description;
    private String location;
    private float salaryMin;
    private float salaryMax;
    private String type;
    private LocalDateTime postedAt;
    private String employerName;
    private String experience;
    private Integer vacancies;

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

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

    public LocalDateTime getPostedAt() { return postedAt; }
    public void setPostedAt(LocalDateTime postedAt) { this.postedAt = postedAt; }

    public String getEmployerName() { return employerName; }
    public void setEmployerName(String employerName) { this.employerName = employerName; }

    public String getExperience() { return experience; }
    public void setExperience(String experience) { this.experience = experience; }

    public Integer getVacancies() { return vacancies; }
    public void setVacancies(Integer vacancies) { this.vacancies = vacancies; }
}
