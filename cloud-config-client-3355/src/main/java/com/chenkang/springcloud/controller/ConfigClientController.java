package com.chenkang.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ChenKang
 * @date 2023/11/13 16:04
 */
@RestController
@RefreshScope   //自动化标签  当github上的配合修改 之后 加上该 注解会动态的获取最新的配置信息
public class ConfigClientController {


    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/configInfo")
    public String getConfigInfo()
    {
        return configInfo;
    }
}
