package com.paikcafe.pos.backend.demo.service;

import com.paikcafe.pos.backend.demo.dto.RobotPositionDto;
import com.paikcafe.pos.backend.demo.entity.RobotPosition;
import com.paikcafe.pos.backend.demo.repository.RobotPositionRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RobotPositionService {

    @Autowired
    private RobotPositionRepository robotPositionRepository;

    @Transactional
    public RobotPosition createOrUpdatePosition(RobotPositionDto dto) {
        robotPositionRepository.deleteByFromKeyAndToKey(dto.getFromKey(), dto.getToKey());

        RobotPosition position = new RobotPosition();
        position.setFromKey(dto.getFromKey());
        position.setToKey(dto.getToKey());
        position.setMovementJson(dto.getMovementJson()); // store as-is (seconds)

        return robotPositionRepository.save(position);
    }

    public List<RobotPosition> getAllPositions() {
        return robotPositionRepository.findAll();
    }

    @Transactional
    public void deletePosition(String fromKey, String toKey) {
        robotPositionRepository.deleteByFromKeyAndToKey(fromKey, toKey);
    }
    
    public void clearAllPositions() {
        robotPositionRepository.deleteAll();
    }
}
