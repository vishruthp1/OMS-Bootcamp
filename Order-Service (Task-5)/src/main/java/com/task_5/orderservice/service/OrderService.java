package com.task_5.orderservice.service;


import com.task_5.orderservice.avro.OrderCreatedEvent;
import com.task_5.orderservice.entity.OrderEntity;
import com.task_5.orderservice.orders.OrderRequest;
import com.task_5.orderservice.orders.OrderResponse;
import com.task_5.orderservice.producer.OrderEventProducer;

import com.task_5.orderservice.repository.OrderRepository;
import jakarta.transaction.Transactional;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderService {


    private  final OrderRepository orderRepository;
    private  final OrderEventProducer orderEventProducer;

    public OrderService(OrderRepository orderRepository, OrderEventProducer orderEventProducer) {
        this.orderRepository = orderRepository;
        this.orderEventProducer = orderEventProducer;
    }


    @Transactional
    public OrderResponse createOrder(OrderRequest request){
        log.info("creating order:{}",request);
        OrderEntity entity = OrderEntity.builder()
                .productName(request.getProductName())
                .amount(request.getAmount())
                .build();
        OrderEntity savedEntity = orderRepository.save(entity);
        log.info("saved order:{}",savedEntity);


        OrderCreatedEvent eventService = OrderCreatedEvent.newBuilder()
                .setOrderId(String.valueOf(savedEntity.getId()))
                .setProductName(savedEntity.getProductName())
                .setAmount(savedEntity.getAmount())
                .build();

        orderEventProducer.sendEvent(eventService);

        return OrderResponse.builder()
                .orderId(String.valueOf(savedEntity.getId()))
                .message("Order created successfully")
                .productName(savedEntity.getProductName())
                .amount(savedEntity.getAmount())
                .createdAt(savedEntity.getCreatedAt())
                .build();



    }
}
