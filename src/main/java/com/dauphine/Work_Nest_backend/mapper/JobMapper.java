package com.dauphine.Work_Nest_backend.mapper;

import com.dauphine.Work_Nest_backend.dto.JobRequest;
import com.dauphine.Work_Nest_backend.dto.JobResponse;
import com.dauphine.Work_Nest_backend.entity.Job;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface JobMapper {
    JobMapper INSTANCE = Mappers.getMapper(JobMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "postedAt", ignore = true)
    @Mapping(target = "employer", ignore = true)
    @Mapping(target = "experience", ignore = true)
    @Mapping(target = "vacancies", ignore = true)

    Job toEntity(JobRequest jobRequest);

    @Mapping(source = "employer.name", target = "employerName")
    JobResponse toResponse(Job job);
}
