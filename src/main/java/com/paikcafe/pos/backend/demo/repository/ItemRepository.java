package com.paikcafe.pos.backend.demo.repository;

import com.paikcafe.pos.backend.demo.entity.Item;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {
    Optional<Item> findByName(String name);

    // Add this method for filtering by itemTypeId with sorting
    List<Item> findByItemType_Id(UUID itemTypeId, Sort sort);
    
    long countByIdIn(List<UUID> id);
}
