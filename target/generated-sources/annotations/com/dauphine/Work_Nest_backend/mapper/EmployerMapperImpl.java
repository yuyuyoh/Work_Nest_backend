package com.dauphine.Work_Nest_backend.mapper;

import com.dauphine.Work_Nest_backend.dto.EmployerRequest;
import com.dauphine.Work_Nest_backend.dto.EmployerResponse;
import com.dauphine.Work_Nest_backend.entity.Employer;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-04T12:53:02+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.4 (Eclipse Adoptium)"
)
@Component
public class EmployerMapperImpl implements EmployerMapper {

    @Override
    public Employer toEntity(EmployerRequest request) {
        if ( request == null ) {
            return null;
        }

        Employer employer = new Employer();

        employer.setName( request.getName() );
        employer.setIndustry( request.getIndustry() );
        employer.setAddress( request.getAddress() );
        employer.setPhone( request.getPhone() );

        return employer;
    }

    @Override
    public EmployerResponse toResponse(Employer entity) {
        if ( entity == null ) {
            return null;
        }

        EmployerResponse employerResponse = new EmployerResponse();

        employerResponse.setId( entity.getId() );
        employerResponse.setName( entity.getName() );
        employerResponse.setIndustry( entity.getIndustry() );
        employerResponse.setAddress( entity.getAddress() );
        employerResponse.setPhone( entity.getPhone() );

        return employerResponse;
    }
}
