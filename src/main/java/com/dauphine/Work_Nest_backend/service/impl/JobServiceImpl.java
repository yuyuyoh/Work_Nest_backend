package com.dauphine.Work_Nest_backend.service.impl;

import com.dauphine.Work_Nest_backend.dto.JobRequest;
import com.dauphine.Work_Nest_backend.dto.JobResponse;
import com.dauphine.Work_Nest_backend.entity.Employer;
import com.dauphine.Work_Nest_backend.entity.Job;
import com.dauphine.Work_Nest_backend.enums.JobType;
import com.dauphine.Work_Nest_backend.mapper.JobMapper;
import com.dauphine.Work_Nest_backend.repository.EmployerRepository;
import com.dauphine.Work_Nest_backend.repository.JobRepository;
import com.dauphine.Work_Nest_backend.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final EmployerRepository employerRepository;
    private final JobMapper jobMapper;

    @Override
    public List<JobResponse> getJobsSortedByDate() {
        return jobRepository.findAllOrderByPostedAtDesc()
                .stream()
                .map(jobMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public JobResponse createJob(JobRequest jobRequest) {
        Job job = jobMapper.toEntity(jobRequest);

        Employer employer = employerRepository.findById(jobRequest.getEmployerId())
                .orElseThrow(() -> new RuntimeException("Employeur non trouvé"));

        job.setEmployer(employer);

        Job saved = jobRepository.save(job);
        return jobMapper.toResponse(saved);
    }

    @Override
    public List<JobResponse> getJobsByEmployerId(Integer employerId) {
        List<Job> jobs = jobRepository.findByEmployerId(employerId);
        return jobs.stream()
                .map(jobMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public JobResponse getJobById(Integer id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Offre non trouvée"));
        return jobMapper.toResponse(job);
    }

    @Override
    public void deleteJob(Integer id) {
        jobRepository.deleteById(id);
    }

    @Override
    public List<JobResponse> searchJobs(String title, String location, String type, Float minSalary, Float maxSalary) {
        List<Job> jobs = jobRepository.findAll().stream()
                .filter(job -> title == null || (job.getTitle() != null && job.getTitle().toLowerCase().contains(title.toLowerCase())))
                .filter(job -> location == null || (job.getLocation() != null && job.getLocation().equalsIgnoreCase(location)))
                .filter(job -> type == null || (job.getType() != null && job.getType().name().equalsIgnoreCase(type)))
                .filter(job -> minSalary == null || (job.getSalaryMin() != null && job.getSalaryMin() >= minSalary))
                .filter(job -> maxSalary == null || (job.getSalaryMax() != null && job.getSalaryMax() <= maxSalary))
                .collect(Collectors.toList());

        return jobs.stream()
                .map(jobMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public JobResponse updateJob(Integer id, JobRequest jobRequest) {
        Job existingJob = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Offre non trouvée"));

        Employer employer = employerRepository.findById(jobRequest.getEmployerId())
                .orElseThrow(() -> new RuntimeException("Employeur non trouvé"));

        // Mise à jour des champs
        existingJob.setTitle(jobRequest.getTitle());
        existingJob.setDescription(jobRequest.getDescription());
        existingJob.setLocation(jobRequest.getLocation());
        if (jobRequest.getType() != null) {
            try {
                existingJob.setType(JobType.valueOf(jobRequest.getType().toUpperCase()));
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Type d'emploi invalide : " + jobRequest.getType());
            }
        }

        existingJob.setSalaryMin(jobRequest.getSalaryMin());
        existingJob.setSalaryMax(jobRequest.getSalaryMax());
        existingJob.setExperience(jobRequest.getExperience());
        existingJob.setVacancies(jobRequest.getVacancies());
        existingJob.setEmployer(employer);

        Job updated = jobRepository.save(existingJob);
        return jobMapper.toResponse(updated);
    }

}
