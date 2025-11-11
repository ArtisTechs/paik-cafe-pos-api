package com.paikcafe.pos.backend.demo.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class ItemDto {

    private String name;
    private String description;
    private UUID itemTypeId;
    private List<String> variation;
    private List<BigDecimal> price;
    private String photo;
    private boolean inStock;
    private boolean bestSeller;

    // === Getters and Setters ===

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(UUID itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    public List<String> getVariation() {
        return variation;
    }

    public void setVariation(List<String> variation) {
        this.variation = variation;
    }

    public List<BigDecimal> getPrice() {
        return price;
    }

    public void setPrice(List<BigDecimal> price) {
        this.price = price;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }
    
    public boolean isBestSeller() {
        return bestSeller;
    }

    public void isBestSeller(boolean bestSeller) {
        this.bestSeller = bestSeller;
    }
}
