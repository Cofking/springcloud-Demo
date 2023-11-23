package com.chenkang.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ChenKang
 * @date 2023/11/21 14:47
 */

@EnableDiscoveryClient // 使用spring原生注解  开启 服务注册发现功能  开启 Nacos
@SpringBootApplication
public class Payment9001 {
    public static void main(String[] args) {
        SpringApplication.run(Payment9001.class, args);
        System.out.println("Payment9001.java  启动成功。");
    }
}
