package com.paikcafe.pos.backend.demo.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(
    name = "robot_positions",
    uniqueConstraints = @UniqueConstraint(columnNames = {"from_key", "to_key"})
)
public class RobotPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "from_key", nullable = false, length = 64)
    private String fromKey;

    @Column(name = "to_key", nullable = false, length = 64)
    private String toKey;

    @Lob
    @Column(name = "movement_json", nullable = false, columnDefinition = "TEXT")
    private String movementJson;

    // === Getters and Setters ===

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFromKey() {
        return fromKey;
    }

    public void setFromKey(String fromKey) {
        this.fromKey = fromKey;
    }

    public String getToKey() {
        return toKey;
    }

    public void setToKey(String toKey) {
        this.toKey = toKey;
    }

    public String getMovementJson() {
        return movementJson;
    }

    public void setMovementJson(String movementJson) {
        this.movementJson = movementJson;
    }
}
