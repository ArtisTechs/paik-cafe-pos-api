package com.paikcafe.pos.backend.demo.dto;

import java.math.BigDecimal;
import java.util.UUID;
import com.paikcafe.pos.backend.demo.entity.Item;

public class ItemSummaryDto {
    private UUID id;
    private String name;
    private BigDecimal price;
    private String photo;
    private Boolean inStock;

    public ItemSummaryDto() {}

    public ItemSummaryDto(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.price = item.getPrice();
        this.photo = item.getPhoto();
        this.inStock = item.isInStock();
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
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
}
