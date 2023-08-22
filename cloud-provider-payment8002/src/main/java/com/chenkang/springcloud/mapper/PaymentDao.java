package com.chenkang.springcloud.mapper;

import com.chenkang.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author ChenKang
 * @date 2023/5/11 14:21
 */
@Repository
public interface PaymentDao {
    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") String id);
}
