package com.dauphine.Work_Nest_backend.dto;

public class UpdateCredentialsRequest {
    private String email;
    private String currentPassword;
    private String newPassword;


    // Constructeur par défaut
    public UpdateCredentialsRequest() {}

    // Getters et Setters
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }
    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }


}
