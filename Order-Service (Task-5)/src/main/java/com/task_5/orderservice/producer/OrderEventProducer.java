package com.task_5.orderservice.producer;

import com.task_5.orderservice.avro.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderEventProducer {

    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    @Value("${spring.kafka.topic}")
    private String topic;


    public void sendEvent( OrderCreatedEvent event){
        log.info("sending event to Kaka : {}",event);
        kafkaTemplate.send(topic, String.valueOf( event.getOrderId()),event);
    }
}
