package com.vishruth.order_service.repository;

import com.vishruth.order_service.mongo.OrderEventDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderEventRepository extends MongoRepository<OrderEventDocument, String> {
}

