package com.chenkang.springcloud.service;

import com.chenkang.springcloud.entity.CommonResult;
import com.chenkang.springcloud.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author ChenKang
 * @date 2023/9/14 9:42
 */
@FeignClient(value = "cloud-payment-service")
public interface PaymentFeignApi {

    @GetMapping(value = "/payment/{id}")
    public CommonResult<Payment> getParmentById(@PathVariable("id") String id) ;

    @GetMapping(value = "/feign/timeout")
    public String FeignTimeout();
}
