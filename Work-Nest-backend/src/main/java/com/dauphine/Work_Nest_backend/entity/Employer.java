package com.dauphine.Work_Nest_backend.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employers")
public class Employer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "log_id", referencedColumnName = "log_id")
    private Login login;

    private String name;
    private String industry;
    private String address;
    private String pincode;
    private String executive;
    private String phone;
    private String profile;
    private String logo;
}
