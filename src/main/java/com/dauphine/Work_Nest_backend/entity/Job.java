package com.dauphine.Work_Nest_backend.entity;
import com.dauphine.Work_Nest_backend.enums.JobType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employer_id")
    private Employer employer;

    private String title;
    private String description;
    private String location;

    @Column(name = "salary_min")
    private Float salaryMin;

    @Column(name = "salary_max")
    private Float salaryMax;

    @Enumerated(EnumType.STRING)
    private JobType type; // FULL_TIME, PART_TIME, FREELANCE

    private Integer experience;
    private Integer vacancies;

    @CreationTimestamp
    @Column(name = "posted_at")
    private LocalDateTime postedAt;
}

