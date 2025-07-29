package com.paikcafe.pos.backend.demo.repository;

import com.paikcafe.pos.backend.demo.entity.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ItemTypeRepository extends JpaRepository<ItemType, UUID> {
    Optional<ItemType> findByName(String name);
}
