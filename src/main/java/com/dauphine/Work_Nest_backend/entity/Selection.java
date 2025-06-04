package com.dauphine.Work_Nest_backend.entity;
import com.dauphine.Work_Nest_backend.enums.Decision;
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
@Table(name = "selection")
public class Selection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "application_id")
    private Application application;

    @Enumerated(EnumType.STRING)
    private Decision decision = Decision.PENDING;

    private String feedback;

    @CreationTimestamp
    @Column(name = "decided_at")
    private LocalDateTime decidedAt;
}

