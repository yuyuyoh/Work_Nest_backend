package com.dauphine.Work_Nest_backend.service.impl;

import com.dauphine.Work_Nest_backend.dto.ApplicationRequest;
import com.dauphine.Work_Nest_backend.dto.ApplicationResponse;
import com.dauphine.Work_Nest_backend.entity.Application;
import com.dauphine.Work_Nest_backend.entity.Job;
import com.dauphine.Work_Nest_backend.entity.Jobseeker;
import com.dauphine.Work_Nest_backend.enums.ApplicationStatus;
import com.dauphine.Work_Nest_backend.mapper.ApplicationMapper;
import com.dauphine.Work_Nest_backend.repository.ApplicationRepository;
import com.dauphine.Work_Nest_backend.repository.JobRepository;
import com.dauphine.Work_Nest_backend.repository.JobseekerRepository;
import com.dauphine.Work_Nest_backend.service.ApplicationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final JobseekerRepository jobseekerRepository;
    private final ApplicationMapper applicationMapper;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository,
                                  JobRepository jobRepository,
                                  JobseekerRepository jobseekerRepository,
                                  ApplicationMapper applicationMapper) {
        this.applicationRepository = applicationRepository;
        this.jobRepository = jobRepository;
        this.jobseekerRepository = jobseekerRepository;
        this.applicationMapper = applicationMapper;
    }

    @Override
    public ApplicationResponse applyToJob(ApplicationRequest applicationRequest) {
        Job job = jobRepository.findById(applicationRequest.getJobId())
                .orElseThrow(() -> new RuntimeException("Offre d'emploi introuvable"));
        Jobseeker jobseeker = jobseekerRepository.findById(applicationRequest.getJobseekerId())
                .orElseThrow(() -> new RuntimeException("Candidat introuvable"));

        Application application = new Application();
        application.setJob(job);
        application.setJobseeker(jobseeker);
        application.setStatus(applicationRequest.getStatus() != null ? applicationRequest.getStatus() : ApplicationStatus.SUBMITTED);

        Application saved = applicationRepository.save(application);

        return applicationMapper.toResponse(saved);
    }

    @Override
    public List<ApplicationResponse> getApplicationsByJobseeker(Integer jobseekerId) {
        List<Application> applications = applicationRepository.findByJobseekerId(jobseekerId);
        return applications.stream()
                .map(applicationMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void updateApplicationStatus(Integer id, String status) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidature introuvable"));
        application.setStatus(ApplicationStatus.valueOf(status));
        applicationRepository.save(application);
    }

    @Override
    public List<ApplicationResponse> getApplicationsByJob(Integer jobId) {
        List<Application> applications = applicationRepository.findByJobId(jobId);
        return applications.stream()
                .map(applicationMapper::toResponse)
                .collect(Collectors.toList());
    }

}
