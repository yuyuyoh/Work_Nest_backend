package com.dauphine.Work_Nest_backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

public class JobseekerRequest {
    @NotBlank(message = "First name is mandatory")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password is mandatory")
    private String password;

    private String phone;
    private String education;
    private String specialty;

    // === AJOUT pour inscription complète ===
    private List<WorkExperienceRequest> experiences;
    private List<SkillRequest> skills;

    public JobseekerRequest() {}

    // Getters & setters
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEducation() { return education; }
    public void setEducation(String education) { this.education = education; }

    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }

    public List<WorkExperienceRequest> getExperiences() { return experiences; }
    public void setExperiences(List<WorkExperienceRequest> experiences) { this.experiences = experiences; }

    public List<SkillRequest> getSkills() { return skills; }
    public void setSkills(List<SkillRequest> skills) { this.skills = skills; }
}
