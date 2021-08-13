package com.bberzhou.threadLocal;

import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author: bberzhou@gmail.com
 * @date: 8/13/2021
 * Create By Intellij IDEA
 */
public class ThreadLocalTest {

    //  线程安全的map
    // public static Map<String ,Object> data = new ConcurrentHashMap<>();

    //  这也是线程安全的
    public static Map<String ,Object> data = new Hashtable<>();

    //  生成一个随机数
    private static  Random random = new Random();
    public static class Task implements Runnable{

        @Override
        public void run() {
            //  在run 方法中,随机生成一个变量(线程要关联的数据),然后以当前的线程名为key 保存到map中,

            int i = random.nextInt(1000);//  0-999的随机数

            //  获取当前的线程名
            String name = Thread.currentThread().getName();
            System.out.println("线程名【"+name+"】生成的随机数是："+i);

            //  将线程名和随机数放入到map
            data.put(name,i);

            //  假设这里做了一些工作，就线程休眠一会儿
            try {
                //  5秒中
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new OrderService().creatOrder();

            //  在run() 方法快要结束之前,以当前线程名获取出数据并打印,查看是否可以取出操作。
            //  根据key 值获取value
            Object o = data.get(name);
            System.out.println("在线程名【"+name+"】快要结束时取出关联的数据是："+o);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            //  循环创建几个线程
            new Thread(new Task()).start();
        }
    }

}
