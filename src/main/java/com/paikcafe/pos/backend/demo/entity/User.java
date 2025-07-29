package com.paikcafe.pos.backend.demo.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class User {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;  // hashed password

    @ManyToOne(optional = false)
    @JoinColumn(name = "branch_id")
    private Branch branch;

    // --- Constructors ---
    public User() {}

    public User(String username, String password, Branch branch) {
        this.username = username;
        this.password = password;
        this.branch = branch;
    }

    // --- Getters and Setters ---
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Branch getBranch() { return branch; }
    public void setBranch(Branch branch) { this.branch = branch; }
}
