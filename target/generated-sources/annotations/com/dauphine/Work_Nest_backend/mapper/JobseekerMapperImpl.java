package com.dauphine.Work_Nest_backend.mapper;

import com.dauphine.Work_Nest_backend.dto.JobseekerRequest;
import com.dauphine.Work_Nest_backend.dto.JobseekerResponse;
import com.dauphine.Work_Nest_backend.entity.Jobseeker;
import com.dauphine.Work_Nest_backend.entity.Login;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-04T12:53:02+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.4 (Eclipse Adoptium)"
)
@Component
public class JobseekerMapperImpl implements JobseekerMapper {

    @Override
    public Jobseeker toEntity(JobseekerRequest request) {
        if ( request == null ) {
            return null;
        }

        Jobseeker jobseeker = new Jobseeker();

        jobseeker.setFirstName( request.getFirstName() );
        jobseeker.setLastName( request.getLastName() );
        jobseeker.setPhone( request.getPhone() );
        jobseeker.setEducation( request.getEducation() );
        jobseeker.setSpecialty( request.getSpecialty() );

        return jobseeker;
    }

    @Override
    public JobseekerResponse toResponse(Jobseeker entity) {
        if ( entity == null ) {
            return null;
        }

        JobseekerResponse jobseekerResponse = new JobseekerResponse();

        jobseekerResponse.setEmail( entityLoginEmail( entity ) );
        jobseekerResponse.setResume( entity.getResume() );
        jobseekerResponse.setId( entity.getId() );
        jobseekerResponse.setFirstName( entity.getFirstName() );
        jobseekerResponse.setLastName( entity.getLastName() );
        jobseekerResponse.setPhone( entity.getPhone() );
        jobseekerResponse.setEducation( entity.getEducation() );
        jobseekerResponse.setSpecialty( entity.getSpecialty() );

        return jobseekerResponse;
    }

    private String entityLoginEmail(Jobseeker jobseeker) {
        if ( jobseeker == null ) {
            return null;
        }
        Login login = jobseeker.getLogin();
        if ( login == null ) {
            return null;
        }
        String email = login.getEmail();
        if ( email == null ) {
            return null;
        }
        return email;
    }
}
