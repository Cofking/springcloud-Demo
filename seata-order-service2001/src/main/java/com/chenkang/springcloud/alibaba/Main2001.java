package com.chenkang.springcloud.alibaba;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author ChenKang
 * @date 2023/12/13 16:34
 */
@EnableDiscoveryClient // 使用spring原生注解  开启 服务注册发现功能  开启 Nacos
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)//取消数据源的自动创建
@EnableFeignClients   //开启 远程调用
@MapperScan({"com.chenkang.springcloud.alibaba.dao"})
public class Main2001 {
    public static void main(String[] args) {
        SpringApplication.run(Main2001.class, args);
        System.out.println("Main2001.java  启动成功。");
    }
}
