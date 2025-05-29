package com.dauphine.Work_Nest_backend.service;

import com.dauphine.Work_Nest_backend.entity.Job;

import java.util.List;

public interface JobService {
    Job createJob(Job job);
    List<Job> searchJobs(String keyword, String location, float minSalary, float maxSalary);
    Job getJobById(Integer id);
    void deleteJob(Integer id);
}