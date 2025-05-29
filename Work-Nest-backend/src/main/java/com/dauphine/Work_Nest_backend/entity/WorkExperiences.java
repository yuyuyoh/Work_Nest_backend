package com.dauphine.Work_Nest_backend.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "work_experiences")
public class WorkExperiences {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "jobseeker_id")
    @JsonIgnore // Évite la récursion JSON
    private Jobseeker jobseeker;

    private String title;
    private String company;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
}