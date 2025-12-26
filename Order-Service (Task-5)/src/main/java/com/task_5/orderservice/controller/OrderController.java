package com.task_5.orderservice.controller;

import com.task_5.orderservice.orders.OrderRequest;
import com.task_5.orderservice.orders.OrderResponse;
import com.task_5.orderservice.service.OrderService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderController {

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    private final OrderService orderService;

    @PostMapping("/order")
    public OrderResponse createOrder(@RequestBody OrderRequest request){
        log.info("creating order:{}",request);
        return orderService.createOrder(request);
    }
    @GetMapping("/health")
    public String health(){
        return "service is up and running";
    }
}
