package com.dauphine.Work_Nest_backend.mapper;

import com.dauphine.Work_Nest_backend.dto.JobseekerRequest;
import com.dauphine.Work_Nest_backend.dto.JobseekerResponse;
import com.dauphine.Work_Nest_backend.entity.Jobseeker;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface JobseekerMapper {
    JobseekerMapper INSTANCE = Mappers.getMapper(JobseekerMapper.class);

    Jobseeker toEntity(JobseekerRequest request);

    @Mapping(target = "email", source = "login.email")
    @Mapping(target = "resume", source = "resume")
    JobseekerResponse toResponse(Jobseeker entity);
}
