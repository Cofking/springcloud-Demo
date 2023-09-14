package com.chenkang.springcloud.controller;

import com.chenkang.springcloud.entity.CommonResult;
import com.chenkang.springcloud.entity.Payment;
import com.chenkang.springcloud.service.PaymentFeignApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ChenKang
 * @date 2023/9/14 9:56
 */
@RestController
public class FeignOrderController {

    @Autowired
    PaymentFeignApi paymentFeignApi;

    @GetMapping(value = "/consumer/payment/{id}")
    public CommonResult<Payment> getParmentById(@PathVariable("id") String id) {
        return paymentFeignApi.getParmentById(id);
    }

    @GetMapping(value = "/consumer/feign/timeout")
    public String feignTimeOut(){
        return  paymentFeignApi.FeignTimeout();
    }
}
