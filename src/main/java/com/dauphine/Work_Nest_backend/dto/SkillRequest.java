package com.dauphine.Work_Nest_backend.dto;

import jakarta.validation.constraints.NotBlank;

public class SkillRequest {
    @NotBlank(message = "Skill name is mandatory")
    private String name;

    @NotBlank(message = "Skill level is mandatory")
    private String level;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }
}
