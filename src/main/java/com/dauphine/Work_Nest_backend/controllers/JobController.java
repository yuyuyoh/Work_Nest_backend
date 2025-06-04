package com.dauphine.Work_Nest_backend.controllers;

import com.dauphine.Work_Nest_backend.dto.JobRequest;
import com.dauphine.Work_Nest_backend.dto.JobResponse;
import com.dauphine.Work_Nest_backend.entity.Jobseeker;
import com.dauphine.Work_Nest_backend.repository.JobseekerRepository;
import com.dauphine.Work_Nest_backend.service.JobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@Tag(name = "Jobs", description = "Gestion des offres d'emploi")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @Operation(summary = "Crée une nouvelle offre")
    @PostMapping
    public ResponseEntity<JobResponse> createJob(@RequestBody JobRequest jobRequest) {
        return ResponseEntity.ok(jobService.createJob(jobRequest));
    }

    @Operation(summary = "Liste toutes les offres")
    @GetMapping
    public ResponseEntity<List<JobResponse>> getAllJobs(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Float minSalary,
            @RequestParam(required = false) Float maxSalary
    ) {
        System.out.println(" Appel /api/jobs avec filtres : " +
                "title=" + title + ", location=" + location + ", type=" + type +
                ", minSalary=" + minSalary + ", maxSalary=" + maxSalary);

        return ResponseEntity.ok(jobService.searchJobs(title, location, type, minSalary, maxSalary));
    }

    @Operation(summary = "Récupère une offre par ID")
    @GetMapping("/{id}")
    public ResponseEntity<JobResponse> getJobById(@PathVariable Integer id) {
        return ResponseEntity.ok(jobService.getJobById(id));
    }

    @Operation(summary = "Supprime une offre")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Integer id) {
        jobService.deleteJob(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Recherche avancée d'offres d'emploi")
    @GetMapping("/search")
    public ResponseEntity<List<JobResponse>> searchJobs(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Float minSalary,
            @RequestParam(required = false) Float maxSalary
    ) {
        List<JobResponse> jobs = jobService.searchJobs(title, location, type, minSalary, maxSalary);
        return ResponseEntity.ok(jobs);
    }

    @Operation(summary = "Modifie une offre existante")
    @PutMapping("/{id}")
    public ResponseEntity<JobResponse> updateJob(@PathVariable Integer id, @RequestBody JobRequest jobRequest) {
        JobResponse updated = jobService.updateJob(id, jobRequest);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Voir les offres d’un employeur")
    @GetMapping("/employer/{employerId}")
    public ResponseEntity<List<JobResponse>> getJobsByEmployer(@PathVariable Integer employerId) {
        List<JobResponse> jobs = jobService.getJobsByEmployerId(employerId);
        return ResponseEntity.ok(jobs);
    }

    @Operation(summary = "Liste des offres triées par date")
    @GetMapping("/sorted-by-date")
    public ResponseEntity<List<JobResponse>> getJobsSortedByDate() {
        return ResponseEntity.ok(jobService.getJobsSortedByDate());
    }



}
