package com.chenkang.springcloud.service;

import com.chenkang.springcloud.entity.Payment;


/**
 * @author ChenKang
 * @date 2023/5/11 17:10
 */
public interface PaymentService {
    int create(Payment payment);

    Payment getPaymentById(String id);

}
