package com.dauphine.Work_Nest_backend.controllers;

import com.dauphine.Work_Nest_backend.dto.ApplicationResponse;
import com.dauphine.Work_Nest_backend.dto.JobseekerResponse;
import com.dauphine.Work_Nest_backend.dto.UpdateCredentialsRequest;
import com.dauphine.Work_Nest_backend.entity.Jobseeker;
import com.dauphine.Work_Nest_backend.entity.Login;
import com.dauphine.Work_Nest_backend.mapper.JobseekerMapper;
import com.dauphine.Work_Nest_backend.repository.JobseekerRepository;
import com.dauphine.Work_Nest_backend.repository.LoginRepository;
import com.dauphine.Work_Nest_backend.service.ApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobseekers")
@Tag(name = "Jobseekers", description = "Gestion des candidats")
public class JobseekerController {

    private final JobseekerRepository jobseekerRepository;
    private final ApplicationService applicationService;
    private final JobseekerMapper jobseekerMapper;
    private final LoginRepository loginRepository;

    public JobseekerController(JobseekerRepository jobseekerRepository,
                               ApplicationService applicationService,
                               JobseekerMapper jobseekerMapper,
                               LoginRepository loginRepository) {
        this.jobseekerRepository = jobseekerRepository;
        this.applicationService = applicationService;
        this.jobseekerMapper = jobseekerMapper;
        this.loginRepository = loginRepository;
    }

    @Operation(summary = "Récupère un candidat par ID")
    @GetMapping("/{id}")
    public ResponseEntity<JobseekerResponse> getJobseekerById(@PathVariable Integer id) {
        return jobseekerRepository.findById(id)
                .map(jobseeker -> ResponseEntity.ok(jobseekerMapper.toResponse(jobseeker)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Liste les candidatures d'un candidat")
    @GetMapping("/{id}/applications")
    public ResponseEntity<List<ApplicationResponse>> getApplicationsByJobseeker(@PathVariable Integer id) {
        List<ApplicationResponse> applications = applicationService.getApplicationsByJobseeker(id);
        return ResponseEntity.ok(applications);
    }

    @Operation(summary = "Met à jour l'email ou le mot de passe du compte du candidat")
    @PutMapping("/{id}/credentials")
    public ResponseEntity<?> updateCredentials(
            @PathVariable Integer id,
            @RequestBody UpdateCredentialsRequest request) {

        return jobseekerRepository.findById(id)
                .map(jobseeker -> {
                    Login login = jobseeker.getLogin();

                    // Vérifier le mot de passe actuel
                    if (!request.getCurrentPassword().equals(login.getPassword())) {
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                .body("Mot de passe actuel incorrect");
                    }

                    // Vérifier que l'email n'est pas déjà utilisé (et qu'il a changé)
                    if (request.getEmail() != null && !request.getEmail().isBlank()
                            && !request.getEmail().equals(login.getEmail())) {
                        if (loginRepository.existsByEmail(request.getEmail())) {
                            return ResponseEntity.status(HttpStatus.CONFLICT)
                                    .body("Cet email est déjà utilisé !");
                        }
                        login.setEmail(request.getEmail());
                    }

                    // Met à jour le mot de passe si fourni
                    if (request.getNewPassword() != null && !request.getNewPassword().isBlank()) {
                        login.setPassword(request.getNewPassword());
                    }

                    loginRepository.save(login);

                    return ResponseEntity.ok("Identifiants mis à jour avec succès");
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
