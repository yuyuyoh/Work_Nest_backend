package com.dauphine.Work_Nest_backend.controllers;

import com.dauphine.Work_Nest_backend.dto.*;
import com.dauphine.Work_Nest_backend.entity.Jobseeker;
import com.dauphine.Work_Nest_backend.mapper.JobseekerMapper;
import com.dauphine.Work_Nest_backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Users", description = "Inscription, gestion et fichiers des utilisateurs")
public class UserController {

    private final UserService userService;
    private final JobseekerMapper jobseekerMapper;

    public UserController(UserService userService, JobseekerMapper jobseekerMapper) {
        this.userService = userService;
        this.jobseekerMapper = jobseekerMapper;
    }

    // 1. Créer un candidat
    @Operation(summary = "Inscription d’un candidat")
    @PostMapping("/jobseekers")
    public ResponseEntity<JobseekerResponse> createJobseeker(@RequestBody JobseekerRequest jobseekerRequest) {
        return ResponseEntity.ok(userService.createJobseeker(jobseekerRequest));
    }

    // 2. Créer un employeur
    @Operation(summary = "Inscription d’un employeur")
    @PostMapping("/employers")
    public ResponseEntity<EmployerResponse> createEmployer(@RequestBody EmployerRequest employerRequest) {
        return ResponseEntity.ok(userService.createEmployer(employerRequest));
    }

    // 3. Mettre à jour le profil d’un candidat
    @Operation(summary = "Mise à jour du profil d’un candidat")
    @PutMapping("/jobseekers/{id}")
    public ResponseEntity<JobseekerResponse> updateJobseeker(
            @PathVariable Integer id,
            @RequestBody JobseekerRequest updates
    ) {
        return ResponseEntity.ok(userService.updateJobseekerProfile(id, updates));
    }

    @GetMapping("/jobseekers/{id}")
    public ResponseEntity<JobseekerResponse> getJobseekerById(@PathVariable Integer id) {
        // On récupère l'entité depuis le service
        Jobseeker jobseeker = userService.getJobseekerById(id);
        System.out.println(">>> Résumé dans l'entité : " + jobseeker.getResume()); // Déjà présent

        // On la mappe vers le DTO pour la réponse API
        JobseekerResponse response = jobseekerMapper.toResponse(jobseeker);

        // *** Ajoute ce log ***
        System.out.println(">>> Résumé dans le DTO : " + response.getResume());

        return ResponseEntity.ok(response);
    }


    // 5. Upload du CV
    @Operation(summary = "Téléversement du CV d’un candidat")
    @PostMapping(
            value = "/jobseekers/{id}/upload-cv",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<String> uploadResume(
            @PathVariable Integer id,
            @RequestPart("file") MultipartFile file
    ) {
        String path = userService.uploadResume(id, file);
        return ResponseEntity.ok("CV uploaded successfully: " + path);
    }

    // 6. Upload de la photo
    @Operation(summary = "Téléversement de la photo de profil d’un candidat")
    @PostMapping(
            value = "/jobseekers/{id}/upload-photo",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<String> uploadPhoto(
            @PathVariable Integer id,
            @RequestPart("file") MultipartFile file
    ) {
        String path = userService.uploadPhoto(id, file);
        return ResponseEntity.ok("Photo uploaded successfully: " + path);
    }


}
