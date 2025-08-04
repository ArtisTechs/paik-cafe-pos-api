package com.paikcafe.pos.backend.demo.service;

import com.paikcafe.pos.backend.demo.dto.BranchDto;
import com.paikcafe.pos.backend.demo.entity.Branch;
import com.paikcafe.pos.backend.demo.repository.BranchRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BranchService {
    @Autowired
    private BranchRepository branchRepository;

    public Branch createBranch(BranchDto branchDto) {
        // You can check for duplicate branch names here if needed
        Branch branch = new Branch();
        branch.setName(branchDto.getName());
        return branchRepository.save(branch);
    }
    
    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }
    
    public Branch getBranchByName(String name) {
        return branchRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Branch not found with name: " + name));
    }
}
