package com.paikcafe.pos.backend.demo.controller;

import com.paikcafe.pos.backend.demo.dto.RobotPositionDto;
import com.paikcafe.pos.backend.demo.entity.RobotPosition;
import com.paikcafe.pos.backend.demo.service.RobotPositionService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/robot-positions")
public class RobotPositionController {

    @Autowired
    private RobotPositionService robotPositionService;

    private String currentPosition = "starting";

    @PostMapping
    public RobotPosition createOrUpdatePosition(@Valid @RequestBody RobotPositionDto dto) {
        return robotPositionService.createOrUpdatePosition(dto);
    }

    @GetMapping
    public List<RobotPosition> getAllPositions() {
        return robotPositionService.getAllPositions();
    }

    @DeleteMapping
    public ResponseEntity<String> deletePosition(
            @RequestParam String fromKey,
            @RequestParam String toKey) {
        robotPositionService.deletePosition(fromKey, toKey);
        return ResponseEntity.ok("Robot position deleted successfully.");
    }

    @GetMapping("/current")
    public ResponseEntity<String> getCurrentPosition() {
        return ResponseEntity.ok(currentPosition);
    }

    @PutMapping("/current")
    public ResponseEntity<String> updateCurrentPosition(@RequestParam String position) {
        List<String> validPositions = List.of("starting", "table1", "table2", "table3");
        if (!validPositions.contains(position.toLowerCase())) {
            return ResponseEntity.badRequest().body("Invalid position. Must be one of: starting, table1, table2, table3.");
        }
        currentPosition = position.toLowerCase();
        return ResponseEntity.ok("Current position updated to: " + currentPosition);
    }
    
    @DeleteMapping("/clear")
    public ResponseEntity<String> clearAllPositions() {
        robotPositionService.clearAllPositions();
        return ResponseEntity.ok("All robot positions cleared successfully.");
    }

}
