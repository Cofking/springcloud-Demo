package com.chenkang.springcloud.service.impl;

import com.chenkang.springcloud.entity.Payment;
import com.chenkang.springcloud.mapper.PaymentDao;
import com.chenkang.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ChenKang
 * @date 2023/5/11 17:11
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(String id) {
        return paymentDao.getPaymentById(id);
    }
}

