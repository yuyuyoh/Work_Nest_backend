package com.dauphine.Work_Nest_backend.mapper;

import com.dauphine.Work_Nest_backend.dto.WorkExperienceRequest;
import com.dauphine.Work_Nest_backend.entity.WorkExperience;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-04T12:53:02+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.4 (Eclipse Adoptium)"
)
@Component
public class WorkExperienceMapperImpl implements WorkExperienceMapper {

    @Override
    public WorkExperience toEntity(WorkExperienceRequest request) {
        if ( request == null ) {
            return null;
        }

        WorkExperience workExperience = new WorkExperience();

        workExperience.setTitle( request.getTitle() );
        workExperience.setCompany( request.getCompany() );
        workExperience.setStartDate( request.getStartDate() );
        workExperience.setEndDate( request.getEndDate() );
        workExperience.setDescription( request.getDescription() );

        return workExperience;
    }
}
