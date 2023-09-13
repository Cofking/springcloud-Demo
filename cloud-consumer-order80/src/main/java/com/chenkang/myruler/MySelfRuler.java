package com.chenkang.myruler;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义规则类不能放到 @ComponentScan 所扫描的当前包下以及子包下
 * 否则这个配置类会被所有的Ribbon客户端共享，达不到特殊化定制的目的
 * 负载均衡轮询算法: rest接口第几次请求数%服务器集群总数量 = 实际调用服务器位置下标，每次服务重启动后rest接口计数从1开始。
 * @author ChenKang
 * @date 2023/8/23 14:44
 */
@Configuration
public class MySelfRuler {

    @Bean
    public IRule myRuler(){
        return new RandomRule();//随机
    }
}
