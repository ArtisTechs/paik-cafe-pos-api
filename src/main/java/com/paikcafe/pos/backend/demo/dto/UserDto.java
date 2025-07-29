package com.paikcafe.pos.backend.demo.dto;

import java.util.UUID;

public class UserDto {
    private String username;
    private String password;
    private UUID branchId;

    // --- Constructors ---
    public UserDto() {}

    public UserDto(String username, String password, UUID branchId) {
        this.username = username;
        this.password = password;
        this.branchId = branchId;
    }

    // --- Getters & Setters ---
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public UUID getBranchId() { return branchId; }
    public void setBranchId(UUID branchId) { this.branchId = branchId; }
}
