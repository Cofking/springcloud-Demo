package com.chenknag.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author ChenKang
 * @date 2023/10/20 10:30
 */
@Component
public class FeignHystrixFallbackApi implements FeignHystrixApi{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "Feign ok  服务降级";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "Feign timeout  服务降级";
    }
}
