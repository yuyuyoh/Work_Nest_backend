package com.dauphine.Work_Nest_backend.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "jobseekers")
public class Jobseeker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @OneToOne
    @JoinColumn(name = "log_id", referencedColumnName = "log_id")
    private Login login;

    private String firstName;
    private String lastName;
    private String phone;
    private LocalDate dob;
    private String education;
    private String specialty;
    private String resume;
    private String photo;
}