package com.zerowasteshop.repository;

import com.zerowasteshop.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
