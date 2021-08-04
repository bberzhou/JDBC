package com.bberzhou.servletRequestRedirect;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @description:
 * @author: bberzhou@gmail.com
 * @date: 8/3/2021
 * Create By Intellij IDEA
 */
public class Response1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Response1 的请求响应");
        //  请求重定向的方式一：设置响应状态码和 请求头

        //  设置响应状态码302，表示重定向，（）
        // resp.setStatus(302);
        // resp.setHeader("Location","http://localhost:8080/servlet_08/response2");

        //  访问工程以外的资源，请求重定向是可以访问工程以外的资源的
        // resp.setHeader("Location","https://www.baidu.com");


        //  请求重定向的方式二：使用sendRedirect()方法
        resp.sendRedirect("http://localhost:8080/servlet_08/response2");


    }
}
