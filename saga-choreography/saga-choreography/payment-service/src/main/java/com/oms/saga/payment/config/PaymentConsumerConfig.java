package com.oms.saga.payment.config;

import com.oms.saga.commons.event.OrderEvent;
import com.oms.saga.commons.event.OrderStatus;
import com.oms.saga.commons.event.PaymentEvent;
import com.oms.saga.commons.event.PaymentStatus;
import com.oms.saga.payment.service.PaymentService;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Configuration
public class PaymentConsumerConfig {

    @Autowired
    private PaymentService paymentService;

    @Bean
    public Function<Flux<OrderEvent>, Flux<PaymentEvent>> paymentProcessor(){
        return orderEventFlux -> orderEventFlux.flatMap(this::processPayment);
    }

    private Mono<PaymentEvent> processPayment(OrderEvent orderEvent) {
        // get the user id
        // check the balance availability
        // if balance sufficient -> Payment Completed and deduct amount from DB
        // if Payment not sufficient -> cancel the order event and update the amount in DB
        if(OrderStatus.ORDER_CREATED.equals(orderEvent.getOrderStatus())){
            return Mono.fromSupplier(() -> this.paymentService.newOrderEvent(orderEvent));
        } else {
            return Mono.fromRunnable(() -> this.paymentService.cancelOrderEvent(orderEvent));
        }
    }
}
