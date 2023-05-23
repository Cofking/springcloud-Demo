package com.chenkang.springcloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ChenKang
 * @date 2023/5/12 10:01
 */
@SpringBootApplication
public class Order80 {
    public static void main(String[] args) {
        SpringApplication.run(Order80.class,args);
        System.out.println("Order80.class 启动成功。");
    }
}
