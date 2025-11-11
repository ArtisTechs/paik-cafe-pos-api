package com.paikcafe.pos.backend.demo.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import com.paikcafe.pos.backend.demo.entity.Item;

public class ItemSummaryDto {
    private UUID id;
    private String name;
    private List<BigDecimal> price;
    private String photo;
    private Boolean inStock;
    private Boolean bestSeller;

    public ItemSummaryDto() {}

    public ItemSummaryDto(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.price = item.getPrice();
        this.photo = item.getPhoto();
        this.inStock = item.isInStock();
        this.bestSeller = item.isBestSeller();
    }

    // Getters and Setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Boolean getInStock() {
        return inStock;
    }

    public void setInStock(Boolean inStock) {
        this.inStock = inStock;
    }
    
    public boolean isBestSeller() {
        return bestSeller;
    }

    public void setBestSeller(boolean bestSeller) {
        this.bestSeller = bestSeller;
    }
}
