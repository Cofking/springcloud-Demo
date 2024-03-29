package com.chenkang.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan("com.chenkang.springcloud.mapper")
@EnableEurekaClient
@EnableDiscoveryClient
public class payment8001 {
    public static void main(String[] args) {
        SpringApplication.run(payment8001.class, args);
        System.out.println("payment8001.java  启动成功。");
    }
}
