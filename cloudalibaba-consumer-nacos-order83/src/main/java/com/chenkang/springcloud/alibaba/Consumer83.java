package com.chenkang.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ChenKang
 * @date 2023/11/21 15:28
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Consumer83 {
    public static void main(String[] args) {
        SpringApplication.run(Consumer83.class, args);
        System.out.println("Consumer83.java  启动成功。");
    }
}
