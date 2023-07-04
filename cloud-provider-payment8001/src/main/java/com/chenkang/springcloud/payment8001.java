package com.chenkang.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.chenkang.springcloud.mapper")
public class payment8001 {
    public static void main(String[] args) {
        SpringApplication.run(payment8001.class, args);
        System.out.println("payment8001.java  启动成功。");
    }
}
