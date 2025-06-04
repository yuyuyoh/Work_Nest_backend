package com.dauphine.Work_Nest_backend.controllers;

import com.dauphine.Work_Nest_backend.entity.Jobseeker;
import com.dauphine.Work_Nest_backend.repository.JobseekerRepository;
import com.dauphine.Work_Nest_backend.service.FileStorageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/files")
public class FileController {

    private static final String CV_DIR = "uploads/cv/";
    private static final String PHOTO_DIR = "uploads/photo/";

    private final JobseekerRepository jobseekerRepository;
    // Injecter le service
    private final FileStorageService fileStorageService;

    public FileController(JobseekerRepository jobseekerRepository, FileStorageService fileStorageService) {
        this.jobseekerRepository = jobseekerRepository;
        this.fileStorageService = fileStorageService;
    }


    // Télécharger CV
    @GetMapping("/resume/{filename}")
    public ResponseEntity<Resource> downloadResume(@PathVariable String filename) {
        return downloadFile(CV_DIR, filename);
    }

    // Télécharger Photo
    @GetMapping("/photo/{filename}")
    public ResponseEntity<Resource> downloadPhoto(@PathVariable String filename) {
        return downloadFile(PHOTO_DIR, filename);
    }

    // Supprimer CV + mise à jour BDD
    @DeleteMapping("/resume/{jobseekerId}/{filename}")
    public ResponseEntity<String> deleteResume(
            @PathVariable Integer jobseekerId,
            @PathVariable String filename
    ) {
        ResponseEntity<String> response = deleteFile(CV_DIR, filename);
        if (response.getStatusCode().is2xxSuccessful()) {
            Jobseeker jobseeker = jobseekerRepository.findById(jobseekerId)
                    .orElseThrow(() -> new RuntimeException("Candidat non trouvé"));
            jobseeker.setResume(null);
            jobseekerRepository.save(jobseeker);
        }
        return response;
    }

    // Supprimer Photo + mise à jour BDD
    @DeleteMapping("/photo/{jobseekerId}/{filename}")
    public ResponseEntity<String> deletePhoto(
            @PathVariable Integer jobseekerId,
            @PathVariable String filename
    ) {
        ResponseEntity<String> response = deleteFile(PHOTO_DIR, filename);
        if (response.getStatusCode().is2xxSuccessful()) {
            Jobseeker jobseeker = jobseekerRepository.findById(jobseekerId)
                    .orElseThrow(() -> new RuntimeException("Candidat non trouvé"));
            jobseeker.setPhoto(null);
            jobseekerRepository.save(jobseeker);
        }
        return response;
    }

    // Méthode de téléchargement
    private ResponseEntity<Resource> downloadFile(String directory, String filename) {
        try {
            Path filePath = Paths.get(directory).resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);

        } catch (MalformedURLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // Méthode commune de suppression
    private ResponseEntity<String> deleteFile(String directory, String filename) {
        File file = Paths.get(directory).resolve(filename).toFile();
        if (!file.exists()) {
            return ResponseEntity.status(404).body("Fichier non trouvé.");
        }
        if (file.delete()) {
            return ResponseEntity.ok("Fichier supprimé : " + filename);
        } else {
            return ResponseEntity.status(500).body("Impossible de supprimer le fichier.");
        }
    }


}
