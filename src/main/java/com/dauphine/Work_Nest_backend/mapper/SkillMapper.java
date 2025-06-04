package com.dauphine.Work_Nest_backend.mapper;

import com.dauphine.Work_Nest_backend.dto.SkillRequest;
import com.dauphine.Work_Nest_backend.entity.Skill;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SkillMapper {
    SkillMapper INSTANCE = Mappers.getMapper(SkillMapper.class);

    Skill toEntity(SkillRequest request);
}
