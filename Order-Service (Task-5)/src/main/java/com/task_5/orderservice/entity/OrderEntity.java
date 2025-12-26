package com.task_5.orderservice.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
@Builder

public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
   private String productName;
   private double amount;
   @CreationTimestamp
   @Column(updatable = false)
   private LocalDateTime createdAt;


}
