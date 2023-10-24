package com.chenkang.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author ChenKang
 * @date 2023/10/24 16:34
 */
@SpringBootApplication
@EnableHystrixDashboard
public class main9001 {
    public static void main(String[] args) {
        SpringApplication.run(main9001.class, args);
        System.out.println("main9001.class 启动成功。");
    }
}
