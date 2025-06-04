package com.dauphine.Work_Nest_backend.service;

import com.dauphine.Work_Nest_backend.dto.SkillRequest;
import com.dauphine.Work_Nest_backend.dto.WorkExperienceRequest;
import com.dauphine.Work_Nest_backend.entity.Skill;
import com.dauphine.Work_Nest_backend.entity.WorkExperience;

import java.util.List;

public interface ProfileService {
    Skill addSkillToProfile(SkillRequest skillRequest);
    WorkExperience addExperienceToProfile(WorkExperienceRequest experienceRequest);
    List<Skill> getSkillsByJobseeker(Integer jobseekerId);
    List<WorkExperience> getExperiencesByJobseeker(Integer jobseekerId);
}
