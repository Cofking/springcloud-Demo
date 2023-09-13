package com.chenkang.springcloud;

import com.chenkang.myruler.MySelfRuler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @author ChenKang
 * @date 2023/5/12 10:01
 */
@SpringBootApplication
@EnableEurekaClient
//该注解是 告诉客户端 在调用 服务名为 cloud-payment-service 的时候 执行的算法是 MySelfRuler.class ，这个算法类 不能被@ComponentScan 扫描到，
// 否则是所有的Ribbon客户端共享，达不到特殊化定制的目的（也就是一旦被扫描到，RestTemplate直接不管调用哪个服务都会用指定的算法）。
//负载均衡轮询算法: rest接口第几次请求数%服务器集群总数量 = 实际调用服务器位置下标，每次服务重启动后rest接口计数从1开始。
@RibbonClient(name = "cloud-payment-service",configuration= MySelfRuler.class) // 设置 Ribbon 的 负载均衡 自定义策略
public class Order80 {
    public static void main(String[] args) {
        SpringApplication.run(Order80.class, args);
        System.out.println("Order80.class 启动成功。");
    }
}
