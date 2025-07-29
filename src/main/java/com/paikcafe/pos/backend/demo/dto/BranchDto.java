package com.paikcafe.pos.backend.demo.dto;

public class BranchDto {
    private String name;

    // Constructors
    public BranchDto() {}
    public BranchDto(String name) { this.name = name; }

    // Getter/Setter
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
