package com.chenkang.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan("com.chenkang.springcloud.mapper")
@EnableEurekaClient
public class payment8002 {
    public static void main(String[] args) {
        SpringApplication.run(payment8002.class, args);
        System.out.println("payment8002.java  启动成功。");
    }
}
