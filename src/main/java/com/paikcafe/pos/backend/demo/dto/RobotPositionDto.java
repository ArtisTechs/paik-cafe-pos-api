package com.paikcafe.pos.backend.demo.dto;

import java.util.UUID;

public class RobotPositionDto {

    private UUID id;
    private String fromKey;
    private String toKey;
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
