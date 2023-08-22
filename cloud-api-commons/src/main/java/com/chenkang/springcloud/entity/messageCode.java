package com.chenkang.springcloud.entity;

/**
 * @author ChenKang
 * @date 2023/8/11 14:29
 */
public enum messageCode {
    SUCCESS(200),
    Fail(-1);

    int code;

    messageCode(int code) {
        this.code=code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
