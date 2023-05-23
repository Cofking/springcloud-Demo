package com.chenkang.springcloud;

import com.chenkang.springcloud.entity.Payment;
import com.chenkang.springcloud.mapper.PaymentDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



/**
 * @author ChenKang
 * @date 2023/5/11 16:20
 */
@SpringBootTest
public class test1 {

    @Autowired
    PaymentDao paymentDao;

    @Test
    void test1() {
        Payment entity = new Payment();
        entity.setSerial("123");
        entity.setId(1L);

        paymentDao.create(entity);
    }
}
