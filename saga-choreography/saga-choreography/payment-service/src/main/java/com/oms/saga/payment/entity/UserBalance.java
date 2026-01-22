package com.oms.saga.payment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_balance")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBalance {
    @Id
    private int userId;
    private int price;
}
