package com.chenkang.springcloud.service.Impl;

import cn.hutool.core.util.IdUtil;
import com.chenkang.springcloud.service.HystrixPayment;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @author ChenKang
 * @date 2023/9/15 15:23
 */
@Service
@Slf4j
public class HystrixPaymentImpl implements HystrixPayment {

    @Value("${server.port}")
    private String servicePost;

    @Override
    public String paymentOk(Integer id) {

        return "线程池:  " + Thread.currentThread().getName() + "  paymentInfo_OK,id:  " + id + "\t" + "O(∩_∩)O哈哈~";
    }

    @Override
    //HystrixCommandProperties 中有一些默认配置，比如异常 之类的情况 就会执行 降级方法 也可以 在 commandProperties 中 个性化设计
    @HystrixCommand(fallbackMethod = "paymentTimeoutHandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="3000")  //线程睡眠时间 超过3000毫秒 即 执行paymentTimeoutHandler
    })
    public String paymentTimeout(Integer id) {
        int sen = 4;
//        int a=10/0;
        try {
            TimeUnit.SECONDS.sleep(sen);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池:  " + Thread.currentThread().getName() + " paymentTimeout，id:  " + id + "\t" + "O(∩_∩)O哈哈~" + "  耗时(秒): " + sen;
    }

    public String paymentTimeoutHandler(Integer id) {
        return "线程池:  " + Thread.currentThread().getName() + " paymentTimeoutHandler，id:  " + id + "\t" + "o(╥﹏╥)o" ;
    }


    //===========服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),// 失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id)
    {
        if(id < 0)
        {
            throw new RuntimeException("******id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName()+"\t"+"调用成功，流水号: " + serialNumber;
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id)
    {
        return "id 不能负数，请稍后再试，/(ㄒoㄒ)/~~   id: " +id;
    }

}
