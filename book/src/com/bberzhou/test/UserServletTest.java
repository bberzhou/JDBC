package com.bberzhou.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @description: 使用反射的方式来模拟多个业务下的动态的执行操作
 * @author: bberzhou@gmail.com
 * @date: 8/7/2021
 * Create By Intellij IDEA
 */
public class UserServletTest {
    public void login(){
        System.out.println(" 这是login方法");
    }
    public void register(){
        System.out.println(" 这是register方法");
    }
    public void updateUser(){
        System.out.println(" 这是updateUser方法");
    }
    public void updateUserPassword(){
        System.out.println(" 这是updateUserPassword方法");
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        //  使用反射来模拟，不同的action 执行不同的方法
        String  action = "register";
        try {
            //  根据action 参数查找声明的方法
            // Method declaredMethod = UserServletTest.class.getDeclaredMethod(action);
            // System.out.println(declaredMethod);

            Class<UserServletTest> userServletTestClass = UserServletTest.class;
            //  getDeclaredMethod 方法有两个参数，一个是想找的 方法名， 另外一个是 参数列表，是一个可变数组
            //  获取action业务鉴别字符串，获取相应的业务 方法反射对象
            Method declaredMethod = userServletTestClass.getDeclaredMethod(action);
            //  创建运行时类的对象
            UserServletTest servletTest = userServletTestClass.newInstance();
            //  反射调用，传入运行时对象，如果有形参，还要传入形参的值
            //  invoke() 方法是有返回值的，返回值即为对应类中调用的方法的返回值,并且默认为一个Object类型的
           declaredMethod.invoke(servletTest);


        } catch (NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }


    }
}
