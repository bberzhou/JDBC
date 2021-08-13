package com.bberzhou.threadLocal;

/**
 * @description:
 * @author: bberzhou@gmail.com
 * @date: 8/13/2021
 * Create By Intellij IDEA
 */
public class OrderService {
    public void creatOrder(){
        String name = Thread.currentThread().getName();
        System.out.println("orderService : 当前线程"+name+"中保存的数据是："+ThreadLocalTest.data.get(name));
    }
}
