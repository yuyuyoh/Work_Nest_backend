package com.dauphine.Work_Nest_backend.dto;

public class LoginResponse {
    private String token;
    private String role;
    private Integer userId;

    // Getters and Setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
}