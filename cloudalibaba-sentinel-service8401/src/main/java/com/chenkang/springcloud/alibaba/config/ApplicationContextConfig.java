package com.chenkang.springcloud.alibaba.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author ChenKang
 * @date 2023/5/12 10:35
 */

@Configuration
public class ApplicationContextConfig {

    @LoadBalanced  //开启负载均衡能力
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
