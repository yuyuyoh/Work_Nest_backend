package com.dauphine.Work_Nest_backend.entity;
import com.dauphine.Work_Nest_backend.enums.SkillLevel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "skills")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "jobseeker_id")
    @JsonIgnore
    private Jobseeker jobseeker;

    private String name;

    @Enumerated(EnumType.STRING)
    private SkillLevel level; // BEGINNER, INTERMEDIATE, ADVANCED
}

