package com.paikcafe.pos.backend.demo.repository;

import com.paikcafe.pos.backend.demo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID>, JpaSpecificationExecutor<Order> {
	Optional<Order> findTopByOrderByOrderNoDesc();
}
