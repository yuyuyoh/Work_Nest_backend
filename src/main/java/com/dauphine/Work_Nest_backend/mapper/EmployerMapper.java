package com.dauphine.Work_Nest_backend.mapper;

import com.dauphine.Work_Nest_backend.dto.EmployerRequest;
import com.dauphine.Work_Nest_backend.dto.EmployerResponse;
import com.dauphine.Work_Nest_backend.entity.Employer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployerMapper {
    EmployerMapper INSTANCE = Mappers.getMapper(EmployerMapper.class);

    Employer toEntity(EmployerRequest request);

    EmployerResponse toResponse(Employer entity);
}
