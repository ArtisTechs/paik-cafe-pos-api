package com.paikcafe.pos.backend.demo.service;

import com.paikcafe.pos.backend.demo.dto.ItemTypeDto;
import com.paikcafe.pos.backend.demo.entity.ItemType;
import com.paikcafe.pos.backend.demo.exception.ItemTypeAlreadyExistsException;
import com.paikcafe.pos.backend.demo.exception.ItemTypeNotFoundException;
import com.paikcafe.pos.backend.demo.repository.ItemTypeRepository;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ItemTypeService {

    @Autowired
    private ItemTypeRepository itemTypeRepository;

    public ItemType addItemType(ItemTypeDto dto) {
        if (itemTypeRepository.findByName(dto.getName()).isPresent()) {
            throw new ItemTypeAlreadyExistsException();
        }

        ItemType itemType = new ItemType();
        itemType.setName(dto.getName());
        itemType.setDescription(dto.getDescription());

        return itemTypeRepository.save(itemType);
    }
    
    public List<ItemType> getAllItemTypesSorted(String sortBy, String order) {
        Sort.Direction direction = order.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, sortBy);
        return itemTypeRepository.findAll(sort);
    }
    
    public ItemType updateItemType(UUID id, ItemTypeDto dto) {
        ItemType itemType = itemTypeRepository.findById(id)
            .orElseThrow(ItemTypeNotFoundException::new);

        // Check for duplicate name (only if name is changing)
        itemTypeRepository.findByName(dto.getName())
            .filter(existing -> !existing.getId().equals(id))
            .ifPresent(existing -> {
                throw new ItemTypeAlreadyExistsException();
            });

        itemType.setName(dto.getName());
        itemType.setDescription(dto.getDescription());

        return itemTypeRepository.save(itemType);
    }
    
    public void deleteItemType(UUID id) {
        ItemType itemType = itemTypeRepository.findById(id)
            .orElseThrow(ItemTypeNotFoundException::new);

        itemTypeRepository.delete(itemType);
    }
}
