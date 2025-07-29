package com.paikcafe.pos.backend.demo.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class Branch {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    // --- Constructors ---
    public Branch() {}

    public Branch(String name) {
        this.name = name;
    }

    // --- Getters and Setters ---
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
