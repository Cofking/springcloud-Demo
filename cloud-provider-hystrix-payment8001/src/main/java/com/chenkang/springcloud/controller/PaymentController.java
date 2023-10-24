package com.chenkang.springcloud.controller;

import com.chenkang.springcloud.service.HystrixPayment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ChenKang
 * @date 2023/9/15 15:30
 */
@RestController
@Slf4j
public class PaymentController {
    @Resource
    private HystrixPayment hystrixPayment;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id)
    {
        String result = hystrixPayment.paymentOk(id);
        log.info("*****result: "+result);
        return result;
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id)
    {
        String result = hystrixPayment.paymentTimeout(id);
        log.info("*****result: "+result);
        return result;
    }


    //====服务熔断
    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id)
    {
        String result = hystrixPayment.paymentCircuitBreaker(id);
        log.info("****result: "+result);
        return result;
    }

}
