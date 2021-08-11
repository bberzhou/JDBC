package com.bberzhou.servletRequest;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:
 * @author: bberzhou@gmail.com
 * @date: 8/3/2021
 * Create By Intellij IDEA
 */
public class Servlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求的参数（查看办事材料）
        String username = req.getParameter("username");
        System.out.println("在Servlet2（柜台2）中查看参数（材料）"+username);
        //  查看 柜台1 是否有盖章，获取域参数
        Object key = req.getAttribute("key");
        System.out.println("柜台1是否有章："+key);

        //  这里模拟Servlet2 处理自己的业务
        System.out.println("servlet2 处理自己的业务");

    }
}
