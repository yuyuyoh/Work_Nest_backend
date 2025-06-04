package com.dauphine.Work_Nest_backend.mapper;

import com.dauphine.Work_Nest_backend.dto.ApplicationRequest;
import com.dauphine.Work_Nest_backend.dto.ApplicationResponse;
import com.dauphine.Work_Nest_backend.entity.Application;
import com.dauphine.Work_Nest_backend.entity.Job;
import com.dauphine.Work_Nest_backend.entity.Jobseeker;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-04T15:03:54+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.4 (Eclipse Adoptium)"
)
@Component
public class ApplicationMapperImpl implements ApplicationMapper {

    @Override
    public Application toEntity(ApplicationRequest request) {
        if ( request == null ) {
            return null;
        }

        Application application = new Application();

        application.setJob( jobFromId( request.getJobId() ) );
        application.setJobseeker( jobseekerFromId( request.getJobseekerId() ) );
        application.setStatus( request.getStatus() );

        return application;
    }

    @Override
    public ApplicationResponse toResponse(Application application) {
        if ( application == null ) {
            return null;
        }

        ApplicationResponse applicationResponse = new ApplicationResponse();

        applicationResponse.setJobId( applicationJobId( application ) );
        applicationResponse.setJobTitle( applicationJobTitle( application ) );
        applicationResponse.setJobseekerId( applicationJobseekerId( application ) );
        applicationResponse.setJobseekerPhone( applicationJobseekerPhone( application ) );
        applicationResponse.setJobseekerFirstName( applicationJobseekerFirstName( application ) );
        applicationResponse.setJobseekerLastName( applicationJobseekerLastName( application ) );
        applicationResponse.setJobseekerCV( applicationJobseekerResume( application ) );
        if ( application.getStatus() != null ) {
            applicationResponse.setStatus( application.getStatus().name() );
        }
        applicationResponse.setAppliedAt( application.getAppliedAt() );
        applicationResponse.setId( application.getId() );

        applicationResponse.setJobseekerEmail( application.getJobseeker() != null && application.getJobseeker().getLogin() != null ? application.getJobseeker().getLogin().getEmail() : null );

        return applicationResponse;
    }

    private Integer applicationJobId(Application application) {
        if ( application == null ) {
            return null;
        }
        Job job = application.getJob();
        if ( job == null ) {
            return null;
        }
        Integer id = job.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String applicationJobTitle(Application application) {
        if ( application == null ) {
            return null;
        }
        Job job = application.getJob();
        if ( job == null ) {
            return null;
        }
        String title = job.getTitle();
        if ( title == null ) {
            return null;
        }
        return title;
    }

    private Integer applicationJobseekerId(Application application) {
        if ( application == null ) {
            return null;
        }
        Jobseeker jobseeker = application.getJobseeker();
        if ( jobseeker == null ) {
            return null;
        }
        Integer id = jobseeker.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String applicationJobseekerPhone(Application application) {
        if ( application == null ) {
            return null;
        }
        Jobseeker jobseeker = application.getJobseeker();
        if ( jobseeker == null ) {
            return null;
        }
        String phone = jobseeker.getPhone();
        if ( phone == null ) {
            return null;
        }
        return phone;
    }

    private String applicationJobseekerFirstName(Application application) {
        if ( application == null ) {
            return null;
        }
        Jobseeker jobseeker = application.getJobseeker();
        if ( jobseeker == null ) {
            return null;
        }
        String firstName = jobseeker.getFirstName();
        if ( firstName == null ) {
            return null;
        }
        return firstName;
    }

    private String applicationJobseekerLastName(Application application) {
        if ( application == null ) {
            return null;
        }
        Jobseeker jobseeker = application.getJobseeker();
        if ( jobseeker == null ) {
            return null;
        }
        String lastName = jobseeker.getLastName();
        if ( lastName == null ) {
            return null;
        }
        return lastName;
    }

    private String applicationJobseekerResume(Application application) {
        if ( application == null ) {
            return null;
        }
        Jobseeker jobseeker = application.getJobseeker();
        if ( jobseeker == null ) {
            return null;
        }
        String resume = jobseeker.getResume();
        if ( resume == null ) {
            return null;
        }
        return resume;
    }
}
