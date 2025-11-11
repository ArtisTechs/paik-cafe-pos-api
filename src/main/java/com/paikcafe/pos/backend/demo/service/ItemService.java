package com.paikcafe.pos.backend.demo.service;

import com.paikcafe.pos.backend.demo.dto.ItemDto;
import com.paikcafe.pos.backend.demo.entity.Item;
import com.paikcafe.pos.backend.demo.entity.ItemType;
import com.paikcafe.pos.backend.demo.exception.ItemAlreadyExistsException;
import com.paikcafe.pos.backend.demo.exception.ItemNotFoundException;
import com.paikcafe.pos.backend.demo.exception.ItemTypeNotFoundException;
import com.paikcafe.pos.backend.demo.repository.ItemRepository;
import com.paikcafe.pos.backend.demo.repository.ItemTypeRepository;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemTypeRepository itemTypeRepository;

    public Item createItem(ItemDto dto) {
        if (itemRepository.findByName(dto.getName()).isPresent()) {
            throw new ItemAlreadyExistsException();
        }

        ItemType itemType = itemTypeRepository.findById(dto.getItemTypeId())
                .orElseThrow(ItemTypeNotFoundException::new);
        
     // Require price array, not null or empty
        if (dto.getPrice() == null || dto.getPrice().isEmpty()) {
            throw new IllegalArgumentException("Price list is required and cannot be empty.");
        }

        // If variations exist, price length must match variations
        if (dto.getVariation() != null && !dto.getVariation().isEmpty()) {
            if (dto.getPrice().size() != dto.getVariation().size()) {
                throw new IllegalArgumentException("Price array length must match variation array length.");
            }
        } else {
            // If no variation, there should be only one price
            if (dto.getPrice().size() != 1) {
                throw new IllegalArgumentException("Price list must have exactly one entry when there are no variations.");
            }
        }

        Item item = new Item();
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setItemType(itemType);
        item.setVariation(dto.getVariation());
        item.setPrice(dto.getPrice());
        item.setPhoto(dto.getPhoto());
        item.setInStock(dto.isInStock());
        item.setBestSeller(dto.isBestSeller());

        return itemRepository.save(item);
    }
    
    public List<Item> getItems(UUID itemTypeId, String sortBy, String order) {
        Sort.Direction direction = order.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, sortBy);

        if (itemTypeId != null) {
            return itemRepository.findByItemType_Id(itemTypeId, sort);
        } else {
            return itemRepository.findAll(sort);
        }
    }
    
    public Item updateItem(UUID id, ItemDto dto) {
        Item item = itemRepository.findById(id)
            .orElseThrow(ItemNotFoundException::new);

        // Check for name conflict (ignore current item's name)
        itemRepository.findByName(dto.getName())
            .filter(existing -> !existing.getId().equals(id))
            .ifPresent(existing -> {
                throw new ItemAlreadyExistsException();
            });
        
     // Require price array, not null or empty
        if (dto.getPrice() == null || dto.getPrice().isEmpty()) {
            throw new IllegalArgumentException("Price list is required and cannot be empty.");
        }

        // If variations exist, price length must match variations
        if (dto.getVariation() != null && !dto.getVariation().isEmpty()) {
            if (dto.getPrice().size() != dto.getVariation().size()) {
                throw new IllegalArgumentException("Price array length must match variation array length.");
            }
        } else {
            // If no variation, there should be only one price
            if (dto.getPrice().size() != 1) {
                throw new IllegalArgumentException("Price list must have exactly one entry when there are no variations.");
            }
        }

        // Validate item type
        ItemType itemType = itemTypeRepository.findById(dto.getItemTypeId())
            .orElseThrow(ItemTypeNotFoundException::new);

        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setItemType(itemType);
        item.setVariation(dto.getVariation());
        item.setPrice(dto.getPrice());
        item.setPhoto(dto.getPhoto());
        item.setInStock(dto.isInStock());
        item.setBestSeller(dto.isBestSeller());

        return itemRepository.save(item);
    }

    public void deleteItem(UUID id) {
        Item item = itemRepository.findById(id)
            .orElseThrow(ItemNotFoundException::new);

        itemRepository.delete(item);
    }

}
