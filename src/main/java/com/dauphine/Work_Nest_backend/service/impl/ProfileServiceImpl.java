package com.dauphine.Work_Nest_backend.service.impl;

import com.dauphine.Work_Nest_backend.dto.SkillRequest;
import com.dauphine.Work_Nest_backend.dto.WorkExperienceRequest;
import com.dauphine.Work_Nest_backend.entity.Skill;
import com.dauphine.Work_Nest_backend.entity.WorkExperience;
import com.dauphine.Work_Nest_backend.mapper.SkillMapper;
import com.dauphine.Work_Nest_backend.mapper.WorkExperienceMapper;
import com.dauphine.Work_Nest_backend.repository.SkillRepository;
import com.dauphine.Work_Nest_backend.repository.WorkExperienceRepository;
import com.dauphine.Work_Nest_backend.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final SkillRepository skillRepository;
    private final WorkExperienceRepository workExperienceRepository;
    private final SkillMapper skillMapper;
    private final WorkExperienceMapper workExperienceMapper;

    @Override
    public Skill addSkillToProfile(SkillRequest skillRequest) {
        Skill skill = skillMapper.toEntity(skillRequest);
        return skillRepository.save(skill);
    }

    @Override
    public List<Skill> getSkillsByJobseeker(Integer jobseekerId) {
        return skillRepository.findByJobseekerId(jobseekerId);
    }

    @Override
    public WorkExperience addExperienceToProfile(WorkExperienceRequest experienceRequest) {
        WorkExperience experience = workExperienceMapper.toEntity(experienceRequest);
        return workExperienceRepository.save(experience);
    }

    @Override
    public List<WorkExperience> getExperiencesByJobseeker(Integer jobseekerId) {
        return workExperienceRepository.findByJobseekerId(jobseekerId);
    }
}
