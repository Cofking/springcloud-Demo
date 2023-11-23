package com.chenkang.springcloud.alibaba.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ChenKang
 * @date 2023/11/21 15:02
 */
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/payment/nacos/{id}")
    public String getpayment(@PathVariable("id") String id ){
        return " nacos  注册,Serverport: "+port+" id: "+id;
    }
}
