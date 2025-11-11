package com.paikcafe.pos.backend.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
public class Item {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @ManyToOne(optional = false)
    @JoinColumn(name = "item_type_id")
    private ItemType itemType;

    @ElementCollection
    private List<String> variation;

    @ElementCollection
    private List<BigDecimal> price;

    private String photo;

    private boolean inStock;
    
    private boolean bestSeller;

    // === Getters and Setters ===

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
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
    
    public boolean isBestSeller() { return bestSeller; }
    
    public void setBestSeller(boolean bestSeller) { this.bestSeller = bestSeller; }
}
