package com.paikcafe.pos.backend.demo.controller;

import com.paikcafe.pos.backend.demo.dto.BranchDto;
import com.paikcafe.pos.backend.demo.entity.Branch;
import com.paikcafe.pos.backend.demo.service.BranchService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @PostMapping
    public Branch createBranch(@RequestBody BranchDto branchDto) {
        return branchService.createBranch(branchDto);
    }
    
    @GetMapping
    public List<Branch> getAllBranches() {
        return branchService.getAllBranches();
    }
    
    @GetMapping("/by-name/{name}")
    public Branch getBranchByName(@PathVariable String name) {
        return branchService.getBranchByName(name);
    }

}
	