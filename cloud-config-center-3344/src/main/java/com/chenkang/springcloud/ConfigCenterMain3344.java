package com.chenkang.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author ChenKang
 * @date 2023/11/10 16:20
 */
@SpringBootApplication
@EnableConfigServer  // 作为 config 的服务端
@EnableEurekaClient   // 将 config 注册进 eureka 服务端   config是 客户端
// eureka配置中      register-with-eureka: 默认是true意思就是默认将自己注册进 eureka 中  所以可以不写 @EnableEurekaClient
public class ConfigCenterMain3344 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterMain3344.class, args);
        System.out.println("ConfigCenterMain3344.java  启动成功。");
    }
}
