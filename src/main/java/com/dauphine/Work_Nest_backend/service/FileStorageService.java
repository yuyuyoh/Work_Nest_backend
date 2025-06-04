package com.dauphine.Work_Nest_backend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;

@Service
public class FileStorageService {

    private final Path resumeDir = Paths.get("uploads/cv");
    private final Path photoDir = Paths.get("uploads/photo");

    public FileStorageService() {
        try {
            Files.createDirectories(resumeDir);
            Files.createDirectories(photoDir);
        } catch (IOException e) {
            throw new RuntimeException("Impossible de créer les dossiers de stockage", e);
        }
    }

    public String storeResume(MultipartFile file, Integer jobseekerId) {
        return store(file, resumeDir, "cv_" + jobseekerId + "_");
    }

    public String storePhoto(MultipartFile file, Integer jobseekerId) {
        return store(file, photoDir, "photo_" + jobseekerId + "_");
    }

    private String store(MultipartFile file, Path directory, String prefix) {
        String originalFilename = file.getOriginalFilename();
        String filename = prefix + System.currentTimeMillis() + "_" + originalFilename;

        try {
            Path targetPath = directory.resolve(filename);
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
            return filename;
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de l’enregistrement du fichier : " + originalFilename, e);
        }
    }
}
