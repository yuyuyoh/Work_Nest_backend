package com.dauphine.Work_Nest_backend.repository;
import com.dauphine.Work_Nest_backend.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SkillRepository extends JpaRepository<Skill, Integer> {
    List<Skill> findByJobseekerId(Integer jobseekerId);
    List<Skill> findByNameContainingIgnoreCase(String skillName);
}