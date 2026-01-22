package com.oms.saga.payment.service;

import com.oms.saga.commons.dto.OrderRequestDto;
import com.oms.saga.commons.dto.PaymentRequestDto;
import com.oms.saga.commons.event.OrderEvent;
import com.oms.saga.commons.event.PaymentEvent;
import com.oms.saga.commons.event.PaymentStatus;
import com.oms.saga.payment.entity.UserBalance;
import com.oms.saga.payment.entity.UserTransaction;
import com.oms.saga.payment.repository.UserBalanceRepository;
import com.oms.saga.payment.repository.UserTransactionRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PaymentService {

    @Autowired
    private UserBalanceRepository userBalanceRepository;

    @Autowired
    private UserTransactionRepository userTransactionRepository;

    @PostConstruct
    public void initUserBalanceInDB(){
        userBalanceRepository.saveAll(Stream.of(new UserBalance(101, 5000),
                new UserBalance(102, 10000),
                new UserBalance(103, 20000),
                new UserBalance(104, 30000),
                new UserBalance(105, 40000)).collect(Collectors.toList()));

    }

    // get the user id
    // check the balance availability
    // if balance sufficient -> Payment Completed and deduct amount from DB
    // if Payment not sufficient -> cancel the order event and update the amount in DB
    @Transactional
    public PaymentEvent newOrderEvent(OrderEvent orderEvent) {
        OrderRequestDto orderRequestDto = orderEvent.getOrderRequestDto();
        PaymentRequestDto paymentRequestDto = new PaymentRequestDto(orderRequestDto.getOrderId(), orderRequestDto.getUserId(), orderRequestDto.getAmount());
        return userBalanceRepository.findById(orderRequestDto.getUserId())
                .filter(ub->ub.getPrice() > orderRequestDto.getAmount())
                .map(ub -> {
                    ub.setPrice(ub.getPrice() - orderRequestDto.getAmount());
                    userTransactionRepository.save(new UserTransaction(orderRequestDto.getOrderId(), orderRequestDto.getUserId(), orderRequestDto.getAmount()));
                    return new PaymentEvent(paymentRequestDto, PaymentStatus.PAYMENT_COMPLETED);
                }).orElse(new PaymentEvent(paymentRequestDto, PaymentStatus.PAYMENT_FAILED));
    }

    @Transactional
    public void cancelOrderEvent(OrderEvent orderEvent) {
        userTransactionRepository.findById(orderEvent.getOrderRequestDto().getOrderId())
                .ifPresent(ut -> {
                    userTransactionRepository.delete(ut);
                    userBalanceRepository.findById(ut.getUserId())
                            .ifPresent(ub -> ub.setPrice(ub.getPrice() + ut.getAmount()));
                });
    }
}
