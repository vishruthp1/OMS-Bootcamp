package com.oms.saga.payment.repository;

import com.oms.saga.payment.entity.UserTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserTransactionRepository extends JpaRepository<UserTransaction, Integer> {
}
