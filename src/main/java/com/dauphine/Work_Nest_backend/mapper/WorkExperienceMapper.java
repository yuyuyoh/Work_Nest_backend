package com.dauphine.Work_Nest_backend.mapper;

import com.dauphine.Work_Nest_backend.dto.WorkExperienceRequest;
import com.dauphine.Work_Nest_backend.entity.WorkExperience;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WorkExperienceMapper {
    WorkExperienceMapper INSTANCE = Mappers.getMapper(WorkExperienceMapper.class);

    WorkExperience toEntity(WorkExperienceRequest request);
}
