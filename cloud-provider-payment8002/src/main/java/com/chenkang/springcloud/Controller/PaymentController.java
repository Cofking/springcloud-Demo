package com.chenkang.springcloud.Controller;

import com.chenkang.springcloud.entity.CommonResult;
import com.chenkang.springcloud.entity.Payment;
import com.chenkang.springcloud.entity.messageCode;
import com.chenkang.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;


/**
 * @author ChenKang
 * @date 2023/5/12 9:04
 */
@RestController
@Slf4j
public class PaymentController {


    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment")
    public CommonResult<Integer> create(@RequestBody Payment payment) {
        int i = paymentService.create(payment);
        log.info("插入结果:" + i);
        if (i > 0) {
            return new CommonResult<>(messageCode.SUCCESS.getCode(), "插入数据成功，serverPort："+serverPort, i);
        } else {
            return new CommonResult<>(messageCode.Fail.getCode(), "插入数据失败");
        }
    }

    @GetMapping(value = "/payment/{id}")
    public CommonResult<Payment> getParmentById(@PathVariable("id") String id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果:" + payment);
        if (payment != null) {
            return new CommonResult<Payment>(messageCode.SUCCESS.getCode(), "查询数据成功，serverPort："+serverPort, payment);
        } else {
            return new CommonResult<Payment>(messageCode.Fail.getCode(), "查询数据失败,id:" + id);
        }
    }

    @GetMapping(value = "/feign/timeout")
    public String FeignTimeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

}
