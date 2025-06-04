package com.dauphine.Work_Nest_backend.mapper;

import com.dauphine.Work_Nest_backend.dto.SkillRequest;
import com.dauphine.Work_Nest_backend.entity.Skill;
import com.dauphine.Work_Nest_backend.enums.SkillLevel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-04T12:53:02+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.4 (Eclipse Adoptium)"
)
@Component
public class SkillMapperImpl implements SkillMapper {

    @Override
    public Skill toEntity(SkillRequest request) {
        if ( request == null ) {
            return null;
        }

        Skill skill = new Skill();

        skill.setName( request.getName() );
        if ( request.getLevel() != null ) {
            skill.setLevel( Enum.valueOf( SkillLevel.class, request.getLevel() ) );
        }

        return skill;
    }
}
