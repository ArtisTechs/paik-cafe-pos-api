package com.paikcafe.pos.backend.demo.dto;

import com.paikcafe.pos.backend.demo.enumtype.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class OrderDto {

	private String orderNo;
	private UUID id;
    private List<UUID> itemIds;
    private List<Integer> quantity;
    private List<String> variation;
    private List<ItemSummaryDto> items;
    private BigDecimal totalPrice;
    private BigDecimal cash;
    private BigDecimal changeAmount;
    private OrderStatus orderStatus;
    private LocalDateTime orderTime;

    // === Getters and Setters ===

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public List<ItemSummaryDto> getItems() {
        return items;
    }

    public void setItems(List<ItemSummaryDto> items) {
        this.items = items;
    }
    
    public List<UUID> getItemIds() {
        return itemIds;
    }

    public void setItemIds(List<UUID> itemIds) {
        this.itemIds = itemIds;
    }

    public List<Integer> getQuantity() {
        return quantity;
    }

    public void setQuantity(List<Integer> quantity) {
        this.quantity = quantity;
    }

    public List<String> getVariation() {
        return variation;
    }

    public void setVariation(List<String> variation) {
        this.variation = variation;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public BigDecimal getChangeAmount() {
        return changeAmount;
    }

    public void setChangeAmount(BigDecimal change) {
        this.changeAmount = change;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
    
    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }
}
