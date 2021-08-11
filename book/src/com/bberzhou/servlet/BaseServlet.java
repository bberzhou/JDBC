package com.bberzhou.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @description: 抽象基类，用于做基本的Servlet后台业务，方便其他业务来继承
 * @author: bberzhou@gmail.com
 * @date: 8/7/2021
 * Create By Intellij IDEA
 */
public abstract class BaseServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  设置字符编码
        req.setCharacterEncoding("UTF-8");
        //  获取请求的参数 action
        String action = req.getParameter("action");
        //  直接用当前的对象获取Class，再反射获取方法
        try {
            //  根据传入的action字符串参数来获取具体的 业务方法反射对象 ,同时还需要传入的形参的类型，注意这里后面两个形参的格式
            Method declaredMethod = this.getClass().getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);
            //  调用目标业务方法，传入当前类的一个对象，就是this
            //  注意这里使用 this ，更好，因为继承BaseServlet的子类的对象可以直接传入，不能写死
            //  而且还能避免获取对象类型创建实例这个步骤
            declaredMethod.invoke(this,req,resp);
            // declaredMethod.invoke(UserServlet2.class.newInstance(),req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
