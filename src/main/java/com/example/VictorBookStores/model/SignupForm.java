package com.example.VictorBookStores.model;

import jakarta.validation.constraints.*;

public class SignupForm {
    @NotEmpty
    @Size
    private String username = "";
    @NotEmpty
    @Size
    private String password = "";
    @NotEmpty
    @Size
    private String passwordCheck = "";

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @NotEmpty
    private String role = "USER";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordCheck() {
        return passwordCheck;
    }

    public void setPasswordCheck(String passwordCheck) {
        this.passwordCheck = passwordCheck;
    }
}
