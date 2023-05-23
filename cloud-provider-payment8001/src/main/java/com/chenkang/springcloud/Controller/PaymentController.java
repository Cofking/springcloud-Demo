package com.chenkang.springcloud.Controller;

import com.chenkang.springcloud.entity.CommonResult;
import com.chenkang.springcloud.entity.Payment;
import com.chenkang.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author ChenKang
 * @date 2023/5/12 9:04
 */
@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping(value = "/payment")
    public CommonResult<Integer> create(@RequestBody Payment payment){
        int i = paymentService.create(payment);
        log.info("插入结果:"+i);
        if(i>0){
            return new CommonResult<>(200,"插入数据成功",i);
        }else {
            return new CommonResult<>(-1,"插入数据失败");
        }
    }

    @GetMapping(value = "/payment/{id}")
    public CommonResult<Payment> getParmentById(@PathVariable("id") String id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果:"+payment);
        if(payment!=null){
            return new CommonResult<Payment>(200,"查询数据成功",payment);
        }else {
            return new CommonResult<Payment>(-1,"查询数据失败,id:"+id);
        }
    }

}
