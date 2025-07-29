package com.paikcafe.pos.backend.demo.controller;

import com.paikcafe.pos.backend.demo.dto.ItemTypeDto;
import com.paikcafe.pos.backend.demo.dto.SuccessResponse;
import com.paikcafe.pos.backend.demo.entity.ItemType;
import com.paikcafe.pos.backend.demo.service.ItemTypeService;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/item-types")
public class ItemTypeController {

    @Autowired
    private ItemTypeService itemTypeService;

    @PostMapping
    public ItemType createItemType(@RequestBody ItemTypeDto itemTypeDto) {
        return itemTypeService.addItemType(itemTypeDto);
    }
    
    @GetMapping
    public List<ItemType> getAllItemTypes(
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String order
    ) {
        return itemTypeService.getAllItemTypesSorted(sortBy, order);
    } 
    
    @PutMapping("/{id}")
    public ItemType updateItemType(@PathVariable UUID id, @RequestBody ItemTypeDto itemTypeDto) {
        return itemTypeService.updateItemType(id, itemTypeDto);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteItemType(@PathVariable UUID id) {
        itemTypeService.deleteItemType(id);

        return ResponseEntity.ok(
            new SuccessResponse("Item type deleted successfully")
        );
    }
}
