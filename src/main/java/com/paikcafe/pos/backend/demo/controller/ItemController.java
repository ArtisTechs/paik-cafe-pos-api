package com.paikcafe.pos.backend.demo.controller;

import com.paikcafe.pos.backend.demo.dto.ItemDto;
import com.paikcafe.pos.backend.demo.dto.SuccessResponse;
import com.paikcafe.pos.backend.demo.entity.Item;
import com.paikcafe.pos.backend.demo.service.ItemService;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping
    public Item createItem(@RequestBody ItemDto itemDto) {
        return itemService.createItem(itemDto);
    }
    
    @GetMapping
    public List<Item> getItems(
        @RequestParam(required = false) UUID itemTypeId,
        @RequestParam(defaultValue = "name") String sortBy,
        @RequestParam(defaultValue = "asc") String order
    ) {
        return itemService.getItems(itemTypeId, sortBy, order);
    }
    
    @PutMapping("/{id}")
    public Item updateItem(@PathVariable UUID id, @RequestBody ItemDto itemDto) {
        return itemService.updateItem(id, itemDto);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse> deleteItem(@PathVariable UUID id) {
        itemService.deleteItem(id);
        return ResponseEntity.ok(new SuccessResponse("Item deleted successfully"));
    }

}
