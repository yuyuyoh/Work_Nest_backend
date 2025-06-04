package com.dauphine.Work_Nest_backend.mapper;

import com.dauphine.Work_Nest_backend.dto.JobRequest;
import com.dauphine.Work_Nest_backend.dto.JobResponse;
import com.dauphine.Work_Nest_backend.entity.Employer;
import com.dauphine.Work_Nest_backend.entity.Job;
import com.dauphine.Work_Nest_backend.enums.JobType;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-04T18:16:38+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.4 (Eclipse Adoptium)"
)
@Component
public class JobMapperImpl implements JobMapper {

    @Override
    public Job toEntity(JobRequest jobRequest) {
        if ( jobRequest == null ) {
            return null;
        }

        Job job = new Job();

        job.setTitle( jobRequest.getTitle() );
        job.setDescription( jobRequest.getDescription() );
        job.setLocation( jobRequest.getLocation() );
        job.setSalaryMin( jobRequest.getSalaryMin() );
        job.setSalaryMax( jobRequest.getSalaryMax() );
        if ( jobRequest.getType() != null ) {
            job.setType( Enum.valueOf( JobType.class, jobRequest.getType() ) );
        }

        return job;
    }

    @Override
    public JobResponse toResponse(Job job) {
        if ( job == null ) {
            return null;
        }

        JobResponse jobResponse = new JobResponse();

        jobResponse.setEmployerName( jobEmployerName( job ) );
        jobResponse.setId( job.getId() );
        jobResponse.setTitle( job.getTitle() );
        jobResponse.setDescription( job.getDescription() );
        jobResponse.setLocation( job.getLocation() );
        if ( job.getSalaryMin() != null ) {
            jobResponse.setSalaryMin( job.getSalaryMin() );
        }
        if ( job.getSalaryMax() != null ) {
            jobResponse.setSalaryMax( job.getSalaryMax() );
        }
        if ( job.getType() != null ) {
            jobResponse.setType( job.getType().name() );
        }
        jobResponse.setPostedAt( job.getPostedAt() );
        if ( job.getExperience() != null ) {
            jobResponse.setExperience( String.valueOf( job.getExperience() ) );
        }
        jobResponse.setVacancies( job.getVacancies() );

        return jobResponse;
    }

    private String jobEmployerName(Job job) {
        if ( job == null ) {
            return null;
        }
        Employer employer = job.getEmployer();
        if ( employer == null ) {
            return null;
        }
        String name = employer.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
