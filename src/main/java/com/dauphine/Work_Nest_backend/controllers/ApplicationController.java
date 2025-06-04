package com.dauphine.Work_Nest_backend.controllers;

import com.dauphine.Work_Nest_backend.dto.ApplicationRequest;
import com.dauphine.Work_Nest_backend.dto.ApplicationResponse;
import com.dauphine.Work_Nest_backend.service.ApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    // Soumettre une candidature à une offre
    @PostMapping
    public ResponseEntity<?> applyToJob(@RequestBody ApplicationRequest applicationRequest) {
        try {
            ApplicationResponse response = applicationService.applyToJob(applicationRequest);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            // Erreur serveur : message générique
            return ResponseEntity.status(500).body("Erreur interne lors de la candidature");
        }
    }

    // Toutes les candidatures d'un jobseeker
    @GetMapping("/jobseeker/{jobseekerId}")
    public ResponseEntity<List<ApplicationResponse>> getApplicationsByJobseeker(@PathVariable Integer jobseekerId) {
        return ResponseEntity.ok(applicationService.getApplicationsByJobseeker(jobseekerId));
    }

    // Met à jour le statut d'une candidature
    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(
            @PathVariable Integer id,
            @RequestParam String status
    ) {
        try {
            applicationService.updateApplicationStatus(id, status);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur interne lors du changement de statut");
        }
    }

    // Toutes les candidatures pour une offre (par jobId)
    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<ApplicationResponse>> getApplicationsByJob(@PathVariable Integer jobId) {
        return ResponseEntity.ok(applicationService.getApplicationsByJob(jobId));
    }

}
