package com.task_5.orderservice.repository;

import com.task_5.orderservice.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity,Long> {
}
