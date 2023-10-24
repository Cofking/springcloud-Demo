package com.chenkang.springcloud.service;

import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author ChenKang
 * @date 2023/9/15 15:14
 */
public interface HystrixPayment {
    /**
     * 正常返回
     *
     * @return
     */
    public String paymentOk(Integer id);

    /**
     * 超时返回
     *
     * @return
     */
    public String paymentTimeout(Integer id);


    /**
     * 服务熔断测试
     * @param id
     * @return
     */
    public String paymentCircuitBreaker(@PathVariable("id") Integer id);
}
