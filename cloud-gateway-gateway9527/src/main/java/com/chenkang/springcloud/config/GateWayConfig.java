package com.chenkang.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ChenKang
 * @date 2023/11/6 9:54
 */
@Configuration
public class GateWayConfig {

    //访问 /luyou   会在 https://www.baidu.com/ 后面 拼接 /luyou
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();

        routes.route("luyou_1",
                r -> r.path("/luyou")
                        .uri("https://www.baidu.com/")).build();


        return routes.build();
    }


}
