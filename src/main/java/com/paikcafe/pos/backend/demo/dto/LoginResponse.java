package com.paikcafe.pos.backend.demo.dto;

import java.util.UUID;

public class LoginResponse {
    private UUID id;
    private String username;
    private UUID branchId;
    private String branchName;

    public LoginResponse() {}

    public LoginResponse(UUID id, String username, UUID branchId, String branchName) {
        this.id = id;
        this.username = username;
        this.branchId = branchId;
        this.branchName = branchName;
    }

    // Getters & Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public UUID getBranchId() { return branchId; }
    public void setBranchId(UUID branchId) { this.branchId = branchId; }

    public String getBranchName() { return branchName; }
    public void setBranchName(String branchName) { this.branchName = branchName; }
}
