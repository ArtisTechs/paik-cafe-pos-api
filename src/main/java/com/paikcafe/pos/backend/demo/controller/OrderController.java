package com.paikcafe.pos.backend.demo.controller;

import com.paikcafe.pos.backend.demo.dto.OrderDto;
import com.paikcafe.pos.backend.demo.entity.Order;
import com.paikcafe.pos.backend.demo.enumtype.OrderStatus;
import com.paikcafe.pos.backend.demo.service.OrderService;

import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public Order createOrder(@Valid @RequestBody OrderDto orderDto) {
        return orderService.createOrder(orderDto);
    }

    @GetMapping
    public List<OrderDto> getOrders(
        @RequestParam(required = false) OrderStatus status,
        @RequestParam(required = false)
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        LocalDateTime startDate,
        @RequestParam(required = false)
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        LocalDateTime endDate,
        @RequestParam(defaultValue = "orderTime") String sortBy,
        @RequestParam(defaultValue = "desc") String order
    ) {
        return orderService.getOrders(status, startDate, endDate, sortBy, order);
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable UUID id, @Valid @RequestBody OrderDto orderDto) {
        return orderService.updateOrder(id, orderDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable UUID id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok("Order deleted successfully.");
    }

    @PatchMapping("/{id}/status")
    public Order updateOrderStatus(
            @PathVariable UUID id,
            @RequestParam OrderStatus status
    ) {
        return orderService.updateOrderStatus(id, status);
    }
}
