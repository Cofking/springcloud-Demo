package com.chenkang.springcloud.Controller;

/**
 * 静态内部类方式 懒汉式 单例模式
 *
 * @author ChenKang
 * @date 2023/8/7 10:35
 */
public class Singleton {
    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

    // 防止反射破坏单例模式
    private Singleton() {
        if (SingletonHolder.INSTANCE != null) {
            throw new RuntimeException("不允许通过反射创建实例");
        }
    }

    public static Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
