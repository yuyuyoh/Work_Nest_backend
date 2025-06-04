package com.dauphine.Work_Nest_backend.mapper;

import com.dauphine.Work_Nest_backend.dto.LoginRequest;
import com.dauphine.Work_Nest_backend.dto.LoginResponse;
import com.dauphine.Work_Nest_backend.entity.Login;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LoginMapper {
    LoginMapper INSTANCE = Mappers.getMapper(LoginMapper.class);

    Login toEntity(LoginRequest request);

    LoginResponse toResponse(Login entity);
}
