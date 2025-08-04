package com.paikcafe.pos.backend.demo.service;

import com.paikcafe.pos.backend.demo.dto.ItemSummaryDto;
import com.paikcafe.pos.backend.demo.dto.OrderDto;
import com.paikcafe.pos.backend.demo.entity.Item;
import com.paikcafe.pos.backend.demo.entity.Order;
import com.paikcafe.pos.backend.demo.enumtype.OrderStatus;
import com.paikcafe.pos.backend.demo.exception.ApiException;
import com.paikcafe.pos.backend.demo.exception.ItemNotFoundException;
import com.paikcafe.pos.backend.demo.repository.ItemRepository;
import com.paikcafe.pos.backend.demo.repository.OrderRepository;
import com.paikcafe.pos.backend.demo.specification.OrderSpecification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private ItemRepository itemRepository;

    public Order createOrder(OrderDto dto) {
        List<UUID> itemIds = dto.getItemIds();
        List<Integer> quantity = dto.getQuantity();
        List<String> variation = dto.getVariation();

        // ✅ Validate non-empty items and quantities
        if (itemIds == null || itemIds.isEmpty()) {
            throw new ApiException("Item list cannot be empty", "EMPTY_ITEM_LIST");
        }

        if (quantity == null || quantity.isEmpty()) {
            throw new ApiException("Quantity list cannot be empty", "EMPTY_QUANTITY_LIST");
        }

        // ✅ Validate matching lengths
        if (itemIds.size() != quantity.size()) {
            throw new ApiException("Item IDs and quantities must have the same length", "ITEM_QUANTITY_LENGTH_MISMATCH");
        }

        if (variation != null && variation.size() != itemIds.size()) {
            throw new ApiException("Variation length must match item count", "VARIATION_LENGTH_MISMATCH");
        }
        
        if (dto.getOrderType() == null) {
            throw new ApiException("Order type is required", "ORDER_TYPE_REQUIRED");
        }

        // ✅ Validate that all itemIds exist
     // ✅ Use Set to remove duplicates
        Set<UUID> uniqueItemIds = new HashSet<>(itemIds);

        // ✅ Query DB once
        List<Item> foundItems = itemRepository.findAllById(uniqueItemIds);

        // ✅ Check if any item is missing
        if (foundItems.size() != uniqueItemIds.size()) {
            Set<UUID> foundIds = foundItems.stream().map(Item::getId).collect(Collectors.toSet());
            uniqueItemIds.removeAll(foundIds); // This will now contain only the missing ones
            throw new ApiException("Some item IDs were not found: " + uniqueItemIds, "ITEM_NOT_FOUND");
        }

        // ✅ Validate status
        if (dto.getOrderStatus() == null) {
            throw new ApiException("Order status is required", "INVALID_ORDER_STATUS");
        }

        // ✅ Proceed to create
        Order order = new Order();
        order.setOrderNo(generateOrderNo());
        order.setItemIds(itemIds);
        order.setQuantity(quantity);
        order.setVariation(variation);
        order.setTotalPrice(dto.getTotalPrice());
        order.setCash(dto.getCash());
        order.setChangeAmount(dto.getChangeAmount());
        order.setOrderStatus(dto.getOrderStatus());
        order.setOrderTime(LocalDateTime.now());
        order.setOrderType(dto.getOrderType());

        return orderRepository.save(order);
    }

    
    private String generateOrderNo() {
        Optional<Order> latestOrder = orderRepository.findTopByOrderByOrderNoDesc();

        int nextNumber = latestOrder.map(o -> Integer.parseInt(o.getOrderNo())).orElse(0) + 1;
        return String.format("%06d", nextNumber);
    }
    
    public List<OrderDto> getOrders(OrderStatus status, LocalDateTime start, LocalDateTime end, String sortBy, String order) {
        Sort.Direction direction = order.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, sortBy != null ? sortBy : "orderTime");

        Specification<Order> spec = Specification.where(OrderSpecification.hasStatus(status))
                                                 .and(OrderSpecification.orderTimeBetween(start, end));

        List<Order> orders = orderRepository.findAll(spec, sort);

        return orders.stream().map(o -> {
            OrderDto dto = new OrderDto();
            dto.setId(o.getId());
            dto.setOrderNo(o.getOrderNo());
            dto.setOrderStatus(o.getOrderStatus());
            dto.setCash(o.getCash());
            dto.setChangeAmount(o.getChangeAmount());
            dto.setOrderTime(o.getOrderTime());
            dto.setTotalPrice(o.getTotalPrice());
            dto.setQuantity(o.getQuantity());
            dto.setVariation(o.getVariation());
            dto.setItemIds(o.getItemIds());
            dto.setOrderType(o.getOrderType());

            // Fetch item details
            List<Item> allItems = itemRepository.findAllById(
                    o.getItemIds().stream().distinct().toList()
                );
                Map<UUID, Item> itemMap = allItems.stream()
                    .collect(Collectors.toMap(Item::getId, item -> item));
                List<ItemSummaryDto> itemSummaries = o.getItemIds().stream()
                    .map(itemMap::get)
                    .filter(Objects::nonNull)
                    .map(ItemSummaryDto::new)
                    .collect(Collectors.toList());
                dto.setItems(itemSummaries);

            return dto;
        }).toList();

    }
    
    public Order updateOrder(UUID id, OrderDto dto) {
        Order existingOrder = orderRepository.findById(id)
            .orElseThrow(() -> new ApiException("Order not found", "ORDER_NOT_FOUND"));

        List<UUID> itemIds = dto.getItemIds();
        List<Integer> quantity = dto.getQuantity();
        List<String> variation = dto.getVariation();

        // Validation
        if (itemIds == null || itemIds.isEmpty()) {
            throw new ApiException("Item list cannot be empty", "EMPTY_ITEM_LIST");
        }

        if (quantity == null || quantity.isEmpty()) {
            throw new ApiException("Quantity list cannot be empty", "EMPTY_QUANTITY_LIST");
        }

        if (itemIds.size() != quantity.size()) {
            throw new ApiException("Item IDs and quantities must have the same length", "ITEM_QUANTITY_LENGTH_MISMATCH");
        }

        if (variation != null && variation.size() != itemIds.size()) {
            throw new ApiException("Variation length must match item count", "VARIATION_LENGTH_MISMATCH");
        }
        
        if (dto.getOrderType() == null) {
            throw new ApiException("Order type is required", "ORDER_TYPE_REQUIRED");
        }

        // Ensure all itemIds exist
        Set<UUID> uniqueItemIds = new HashSet<>(itemIds);
        List<Item> foundItems = itemRepository.findAllById(uniqueItemIds);
        if (foundItems.size() != uniqueItemIds.size()) {
            Set<UUID> foundIds = foundItems.stream().map(Item::getId).collect(Collectors.toSet());
            uniqueItemIds.removeAll(foundIds);
            throw new ApiException("Some item IDs were not found: " + uniqueItemIds, "ITEM_NOT_FOUND");
        }

        // Status required
        if (dto.getOrderStatus() == null) {
            throw new ApiException("Order status is required", "INVALID_ORDER_STATUS");
        }

        // Update fields
        existingOrder.setItemIds(itemIds);
        existingOrder.setQuantity(quantity);
        existingOrder.setVariation(variation);
        existingOrder.setTotalPrice(dto.getTotalPrice());
        existingOrder.setCash(dto.getCash());
        existingOrder.setChangeAmount(dto.getChangeAmount());
        existingOrder.setOrderStatus(dto.getOrderStatus());
        existingOrder.setOrderType(dto.getOrderType());

        return orderRepository.save(existingOrder);
    }

    public void deleteOrder(UUID id) {
        boolean exists = orderRepository.existsById(id);
        if (!exists) {
            throw new ApiException("Order not found", "ORDER_NOT_FOUND");
        }

        orderRepository.deleteById(id);
    }
    
    public Order updateOrderStatus(UUID id, OrderStatus newStatus) {
        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new ApiException("Order not found", "ORDER_NOT_FOUND"));

        if (newStatus == null) {
            throw new ApiException("Order status is required", "INVALID_ORDER_STATUS");
        }

        order.setOrderStatus(newStatus);
        return orderRepository.save(order);
    }


}
