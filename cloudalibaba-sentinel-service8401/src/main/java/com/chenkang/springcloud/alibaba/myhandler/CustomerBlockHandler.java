package com.chenkang.springcloud.alibaba.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * @author ChenKang
 * @date 2023/11/30 16:11
 */
public class CustomerBlockHandler {

    public static  String CustomerBlock(int id,BlockException e){
        return "自定义流控异常处理";
    }
    public static  String CustomerError(int id,Throwable e){
        return "自定义运行异常处理";
    }
}
