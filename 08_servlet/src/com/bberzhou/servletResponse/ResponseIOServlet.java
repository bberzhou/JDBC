package com.bberzhou.servletResponse;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @description:
 * @author: bberzhou@gmail.com
 * @date: 8/3/2021
 * Create By Intellij IDEA
 */
public class ResponseIOServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //  字符流
        // resp.getWriter();
        //
        // //  字节流
        // resp.getOutputStream();
        //  如果同时使用这两个流，就会报错，
        //  java.lang.IllegalStateException: getWriter() has already been called for this response

        //  要求：往客户端回传 字符串 数据


        //  方式一
        // System.out.println("编码格式："+resp.getCharacterEncoding());    //  编码格式：UTF-8 这是服务器的编码格式
        // //  通过响应头，设置浏览器也使用UTF-8字符集
        // resp.setHeader("Content-Type","text/html;charset=UTF-8");   //  设置编码格式，这是设置响应头


        //  方式二，同时设置服务器和客户端都使用UTF-8字符集，还设置了响应头，注意此方法一定要在获取对象之前调用才有效。
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        // writer.write("response's content!");

        //  回传中文的情况
        writer.write("大家好，我是渣渣辉");
    }
}
