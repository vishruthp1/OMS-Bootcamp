package com.task_5.orderservice.events;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "order_events")
@Builder
public class OrderEventDocument {


    @Id
    private String id;
    private String orderId;
    private String productName;
    private double amount;
}
