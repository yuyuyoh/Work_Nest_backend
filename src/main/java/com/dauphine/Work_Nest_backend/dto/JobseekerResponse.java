package com.dauphine.Work_Nest_backend.dto;

public class JobseekerResponse {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String education;
    private String specialty;
    private String resume;

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEducation() { return education; }
    public void setEducation(String education) { this.education = education; }

    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }

    public String getResume() { return resume; }
    public void setResume(String resume) { this.resume = resume; }
}
