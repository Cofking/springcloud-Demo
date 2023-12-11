package com.chenkang.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.chenkang.springcloud.alibaba.myhandler.CustomerBlockHandler;
import com.chenkang.springcloud.alibaba.service.Feign;
import com.chenkang.springcloud.alibaba.service.SentinelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author ChenKang
 * @date 2023/11/23 17:06
 */
@RestController
@Slf4j
public class FlowLimitController {

    @Autowired
    Feign feign;

    @Autowired
    SentinelService sentinelService;


    @GetMapping("/testA")
    public String testA() throws InterruptedException {
        log.info(new Date().toString());
        //sentinel 线程数测试
//        Thread.sleep(800);
        //链路测试 失败，版本太低，github给出解决办法无效，放弃。后续用到 解决
//        return  sentinelService.sentinelTset();
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB() {
        log.info(Thread.currentThread().getName() + "\t" + "...testB");
        return "------testB";
    }

    @SentinelResource(value = "HotKey", blockHandler = "errorHandler", fallback = "")
    @RequestMapping("/HotKey")
    public String testC(@RequestParam(value = "p1", required = false) String p1,
                        @RequestParam(value = "p2", required = false) String p2) {


        return "HotKey  p1:" + p1 + "    p2:" + p2;

    }


    public String errorHandler(String p1, String p2, BlockException exception) {

        return "sentinel 自定义降级方法    " + p1 + "/t" + p2;
    }

    @SentinelResource(value = "CustomerBlock",
            blockHandler = "CustomerBlock",   //流控异常处理
            blockHandlerClass = CustomerBlockHandler.class, // 流控异常指定类  自定义类中的处理方法
            fallback = "CustomerError",  //运行异常处理   如果 同时配置了 blockHandler   和 fallback  触发流控就被 blockHandler处理 触发运行异常就被 fallback处理
            fallbackClass = CustomerBlockHandler.class//运行异常处理指定类 自定义类中的处理方法
//            defaultFallback = ""   //如果没有 blockHandler fallback  就触发默认异常
    )

    @RequestMapping("/CustomerBlock/{id}")
    public String testD(@PathVariable("id") int id) {
        if (id == 1) {
            throw new RuntimeException();
        }
        return "自定义异常处理测试";
    }

}
