package com.chenknag.springcloud.controller;


import com.chenknag.springcloud.service.FeignHystrixApi;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ChenKang
 * @date 2023/9/19 9:45
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "paymentTimeout_Global_FallbackMethod")
public class FeignHystrixOrderController {

    @Autowired
    private FeignHystrixApi feignHystrixApi;

    @GetMapping("/cunsumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id)
    {
        String result = feignHystrixApi.paymentInfo_OK(id);
        return result;
    }

    @GetMapping("/cunsumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentTimeoutFallbackMethod",commandProperties = {
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="1500")  //线程睡眠时间 超过 1500 毫秒 即 执行 paymentTimeoutFallbackMethod
//    })
    @HystrixCommand  //不指定 fallbackMethod  即调用全局 fallbackMethod
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id)
    {
        int a=10/0;
        String result = feignHystrixApi.paymentInfo_TimeOut(id);
        return result;
    }

    public String paymentTimeoutFallbackMethod(Integer id) {
        return "我是消费者80，对方支付系统繁忙，请5秒后重试 。o(╥﹏╥)o" ;
    }

    public String paymentTimeout_Global_FallbackMethod() {
        return "全局 Fallback  我是消费者80，对方支付系统繁忙，请5秒后重试 。o(╥﹏╥)o" ;
    }
}
