package com.bberzhou.servletRequest;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * @description:
 * @author: bberzhou@gmail.com
 * @date: 8/3/2021
 * Create By Intellij IDEA
 */
public class ParameterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("----------------------------doGET------------------");
        //  获取请求的参数，用户名
        String username = req.getParameter("username");
        //  获取请求的参数，密码
        String password = req.getParameter("password");
        //  获取请求的参数，兴趣爱好
        // String hobby = req.getParameter("hobby");
        //  当前端传入的复选框这种多个参数的是很好，就不能使用getParameter()方法，应该使用getParameterValues()方法
        String[] hobbies = req.getParameterValues("hobby");
        // for (int i = 0; i < hobbies.length; i++) {
        //     System.out.println("兴趣爱好："+hobbies[i]);
        // }
        System.out.println("用户名："+username);
        System.out.println("密码："+password);
        System.out.println("兴趣爱好："+ Arrays.asList(hobbies));

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //  设置请求体字符集编码为 UTF-8，解决POST请求的中文乱码问题,这个方法setCharacterEncoding()要在获取请求参数之前才有效
        req.setCharacterEncoding("UTF-8");
        System.out.println("----------------------------doPost------------------");
        //  获取请求的参数，用户名
        String username = req.getParameter("username");
        //  获取请求的参数，密码
        String password = req.getParameter("password");
        //  获取请求的参数，兴趣爱好
        // String hobby = req.getParameter("hobby");
        //  当前端传入的复选框这种多个参数的是很好，就不能使用getParameter()方法，应该使用getParameterValues()方法
        String[] hobbies = req.getParameterValues("hobby");
        // for (int i = 0; i < hobbies.length; i++) {
        //     System.out.println("兴趣爱好："+hobbies[i]);
        // }
        System.out.println("用户名："+username);
        System.out.println("密码："+password);
        System.out.println("兴趣爱好："+ Arrays.asList(hobbies));
    }
}
