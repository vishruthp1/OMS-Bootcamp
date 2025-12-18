package com.vishruth.order_service.service;

import com.vishruth.order_service.entity.OrderEntity;
import com.vishruth.order_service.event.OrderCreatedEvent;
import com.vishruth.order_service.kafka.OrderProducer;
import com.vishruth.order_service.mongo.OrderEventDocument;
import com.vishruth.order_service.repository.OrderEventRepository;
import com.vishruth.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.vishruth.order_service.event.OrderCreatedEvent;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderProducer orderProducer;
    private final OrderEventRepository eventRepository;

    public OrderEntity createOrder(OrderEntity order) {

        // 1. Save Order (Postgres)
        OrderEntity savedOrder = orderRepository.save(order);

        // 2. Create Event
        OrderCreatedEvent event = OrderCreatedEvent.newBuilder()
                .setOrderId(savedOrder.getOrderId().toString())
                .setProductName(savedOrder.getProductName())
                .setQuantity(savedOrder.getQuantity())
                .setPrice(savedOrder.getPrice())
                .setCreatedAt(Instant.now().toString())
                .build();

        // 3. Publish to Kafka
        orderProducer.send(event);

        // 4. Persist Event (MongoDB)
        OrderEventDocument doc = new OrderEventDocument();
        doc.setEventType("OrderCreated");
        doc.setAggregateId(savedOrder.getOrderId().toString());
        doc.setPayload(event);
        doc.setCreatedAt(Instant.now());

        eventRepository.save(doc);

        return savedOrder;
    }
}



