package com.dauphine.Work_Nest_backend.service.impl;
import com.dauphine.Work_Nest_backend.entity.Job;
import com.dauphine.Work_Nest_backend.repository.JobRepository;
import com.dauphine.Work_Nest_backend.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    @Override
    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public List<Job> searchJobs(String keyword, String location, float minSalary, float maxSalary) {
        if (keyword != null) {
            return jobRepository.searchByKeyword(keyword);
        } else if (location != null) {
            return jobRepository.findByLocation(location);
        } else if (minSalary > 0 || maxSalary > 0) {
            return jobRepository.findBySalaryRange(minSalary, maxSalary);
        }
        return jobRepository.findAll();
    }

    @Override
    public Job getJobById(Integer id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Offre non trouvée"));
    }

    @Override
    public void deleteJob(Integer id) {
        jobRepository.deleteById(id);
    }
}