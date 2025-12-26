package com.task_5.orderservice.consumer;

import com.task_5.orderservice.avro.OrderCreatedEvent;
import com.task_5.orderservice.events.OrderEventDocument;
import com.task_5.orderservice.repository.OrderEventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderEventConsumer {

    private final OrderEventRepository orderEventRepository;

    public OrderEventConsumer(OrderEventRepository orderEventRepository) {
        this.orderEventRepository = orderEventRepository;
    }

    @KafkaListener(topics = "${spring.kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(OrderCreatedEvent event){
        log.info("consumed event:{}",event);
        try {
            OrderEventDocument doc = OrderEventDocument.builder()
                    .orderId(String.valueOf(event.getOrderId()))
                    .productName(String.valueOf(event.getProductName()))
                    .amount(event.getAmount())
                    .build();

            orderEventRepository.save(doc);
            log.info("saved event to mongodb");
        } catch (Exception e) {
            log.error("Failed to process event: {}", event, e);
        }
    }

}
