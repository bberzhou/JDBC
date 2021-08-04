package com.bberzhou.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @description:
 * @author: bberzhou@gmail.com
 * @date: 8/4/2021
 * Create By Intellij IDEA
 */
public class PrintHtml extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  通过响应的回传流 回传html页面数据

        //  设置响应的字符，
        //  方式一：使用setContentType，同时设置服务器端和客户端
        // resp.setContentType("text/html;charset=UTF-8");

        //  方式二：使用响应头+响应体
        resp.setCharacterEncoding("UTF-8"); //  这是服务器端
        resp.setHeader("Content-Type","text/html;charset=UTF-8");   //  这是设置响应头客户端


        // 如果直接使用响应流进行回传数据，比较复杂


        //  获取响应流
        PrintWriter writer = resp.getWriter();
        writer.write("<!DOCTYPE html>\r\n");
        writer.write("<html lang=\"en\">\r\n");
        writer.write("  <head>\r\n");
        writer.write("  <meta charset=\"UTF-8\">\r\n");
        writer.write("  <title>Title</title>\r\n");
        writer.write("  </head>\r\n");
        writer.write("      <body>\r\n");
        writer.write("          这是html页面数据。\r\n");
        writer.write("      </body>\r\n");
        writer.write("</html>\r\n");












    }
}
