package com.dauphine.Work_Nest_backend.service.impl;

import com.dauphine.Work_Nest_backend.entity.Application;
import com.dauphine.Work_Nest_backend.enums.ApplicationStatus;
import com.dauphine.Work_Nest_backend.repository.ApplicationRepository;
import com.dauphine.Work_Nest_backend.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;

    @Override
    public Application applyToJob(Application application) {
        // Vérifier que le candidat n'a pas déjà postulé
        boolean alreadyApplied = applicationRepository.existsByJobIdAndJobseekerId(
                application.getJob().getId(),
                application.getJobseeker().getId()
        );

        if (alreadyApplied) {
            throw new RuntimeException("Vous avez déjà postulé à cette offre");
        }
        return applicationRepository.save(application);
    }

    @Override
    public List<Application> getApplicationsByJobseeker(Integer jobseekerId) {
        return applicationRepository.findByJobseekerId(jobseekerId);
    }

    @Override
    public void updateApplicationStatus(Integer id, String status) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidature non trouvée"));
        application.setStatus(ApplicationStatus.valueOf(status));
        applicationRepository.save(application);
    }
}