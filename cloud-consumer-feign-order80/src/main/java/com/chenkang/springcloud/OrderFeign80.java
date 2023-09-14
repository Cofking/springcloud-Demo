package com.chenkang.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author ChenKang
 * @date 2023/9/13 17:22
 */
@SpringBootApplication
@EnableFeignClients  //  开启 openFeign 功能
public class OrderFeign80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderFeign80.class, args);
        System.out.println("OrderFeign80.class 启动成功。");
    }
}
