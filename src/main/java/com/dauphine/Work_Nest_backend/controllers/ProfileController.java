package com.dauphine.Work_Nest_backend.controllers;

import com.dauphine.Work_Nest_backend.dto.SkillRequest;
import com.dauphine.Work_Nest_backend.entity.Skill;
import com.dauphine.Work_Nest_backend.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("/{jobseekerId}/skills")
    public ResponseEntity<Skill> addSkill(
            @PathVariable Integer jobseekerId,
            @RequestBody SkillRequest skillRequest
    ) {
        return ResponseEntity.ok(profileService.addSkillToProfile(skillRequest));
    }

    @GetMapping("/{jobseekerId}/skills")
    public ResponseEntity<List<Skill>> getSkills(@PathVariable Integer jobseekerId) {
        return ResponseEntity.ok(profileService.getSkillsByJobseeker(jobseekerId));
    }
}
