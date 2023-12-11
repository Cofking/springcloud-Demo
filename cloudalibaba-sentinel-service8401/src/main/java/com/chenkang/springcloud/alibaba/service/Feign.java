package com.chenkang.springcloud.alibaba.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author ChenKang
 * @date 2023/11/28 14:17
 */
@Component
@FeignClient(value = "nacos-payment-provider")
public interface Feign {


    @GetMapping("/payment/nacos/{id}")
    public String getpayment(@PathVariable("id") String id );

}
