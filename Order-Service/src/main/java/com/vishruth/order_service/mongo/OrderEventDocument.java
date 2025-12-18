package com.vishruth.order_service.mongo;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "order_events")
@Data
public class OrderEventDocument {

    @Id
    private String id;

    private String eventType;
    private String aggregateId;
    private Object payload;
    private Instant createdAt;
}

