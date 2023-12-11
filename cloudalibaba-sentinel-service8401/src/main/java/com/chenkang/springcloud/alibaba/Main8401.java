package com.chenkang.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * @author ChenKang
 * @date 2023/11/23 16:58
 */
@EnableDiscoveryClient // 使用spring原生注解  开启 服务注册发现功能  开启 Nacos
@SpringBootApplication
@EnableFeignClients   //开启 远程调用
public class Main8401 {
    public static void main(String[] args) {
        SpringApplication.run(Main8401.class, args);
        System.out.println("Main8401.java  启动成功。");
    }
}
