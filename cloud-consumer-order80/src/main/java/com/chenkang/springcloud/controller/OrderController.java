package com.chenkang.springcloud.controller;

import com.chenkang.springcloud.entity.CommonResult;
import com.chenkang.springcloud.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 * @author ChenKang
 * @date 2023/5/12 10:25
 */
@Controller
@Slf4j
public class OrderController {

    public static final String PAYMENT_URL="http://localhost:8001";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment")
    @ResponseBody
    public CommonResult<Payment> create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/{id}")
    @ResponseBody
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/"+id,CommonResult.class);
    }

}
