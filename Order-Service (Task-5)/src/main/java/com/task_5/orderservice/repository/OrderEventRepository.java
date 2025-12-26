package com.task_5.orderservice.repository;

import com.task_5.orderservice.events.OrderEventDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderEventRepository extends MongoRepository<OrderEventDocument, String> {
}
