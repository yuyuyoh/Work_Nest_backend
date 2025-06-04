package com.dauphine.Work_Nest_backend.service;

import com.dauphine.Work_Nest_backend.dto.JobRequest;
import com.dauphine.Work_Nest_backend.dto.JobResponse;

import java.util.List;

public interface JobService {
    JobResponse createJob(JobRequest jobRequest);
    JobResponse getJobById(Integer id);
    void deleteJob(Integer id);
    List<JobResponse> searchJobs(String title, String location, String type, Float minSalary, Float maxSalary);
    JobResponse updateJob(Integer id, JobRequest jobRequest);
    List<JobResponse> getJobsByEmployerId(Integer employerId);
    List<JobResponse> getJobsSortedByDate();


}
