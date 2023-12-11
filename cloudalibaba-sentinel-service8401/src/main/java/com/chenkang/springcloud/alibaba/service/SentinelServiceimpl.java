package com.chenkang.springcloud.alibaba.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

/**
 * @author ChenKang
 * @date 2023/11/28 15:34
 */
@Service
public class SentinelServiceimpl  implements SentinelService{


    @SentinelResource("goods")
    @Override
    public String sentinelTset() {

        return "sentinel链路测试";
    }
}
