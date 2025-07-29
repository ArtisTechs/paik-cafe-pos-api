package com.paikcafe.pos.backend.demo.specification;

import com.paikcafe.pos.backend.demo.entity.Order;
import com.paikcafe.pos.backend.demo.enumtype.OrderStatus;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class OrderSpecification {

    public static Specification<Order> hasStatus(OrderStatus status) {
        return (root, query, builder) -> status == null ? null : builder.equal(root.get("orderStatus"), status);
    }

    public static Specification<Order> orderTimeBetween(LocalDateTime start, LocalDateTime end) {
        return (root, query, builder) -> {
            if (start != null && end != null) {
                return builder.between(root.get("orderTime"), start, end);
            } else if (start != null) {
                return builder.greaterThanOrEqualTo(root.get("orderTime"), start);
            } else if (end != null) {
                return builder.lessThanOrEqualTo(root.get("orderTime"), end);
            } else {
                return null;
            }
        };
    }
}
