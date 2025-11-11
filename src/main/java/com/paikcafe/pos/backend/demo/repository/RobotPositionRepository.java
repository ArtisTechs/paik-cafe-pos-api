package com.paikcafe.pos.backend.demo.repository;

import com.paikcafe.pos.backend.demo.entity.RobotPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RobotPositionRepository extends JpaRepository<RobotPosition, UUID> {

    Optional<RobotPosition> findByFromKeyAndToKey(String fromKey, String toKey);

    @Modifying
    @Query("DELETE FROM RobotPosition r WHERE r.fromKey = :fromKey AND r.toKey = :toKey")
    void deleteByFromKeyAndToKey(String fromKey, String toKey);
}
