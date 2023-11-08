package com.chenkang.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;


/**
 * @author ChenKang
 * @date 2023/11/8 14:56
 */
@Component
@Slf4j
public class AMyLogGateWayFilter2 implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String name = exchange.getRequest().getQueryParams().getFirst("name");
        List<String> list = exchange.getRequest().getHeaders().get("token");
        if (name == null) {
            log.info("****拦截****");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_FOUND);
            exchange.getResponse().setComplete();
        }
        log.info("----放行----");
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
