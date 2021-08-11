package com.bberzhou.servletRequest;

import javax.servlet.RequestDispatcher;
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
public class Servlet1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  获取请求的参数，（办事的材料）
        String username = req.getParameter("username");
        System.out.println("这里模拟对获取到的请求参数，做一些相关的操作（在Servlet1 中查看参数）"+username);

        //  对获取到的材料，操作完之后，加盖一个章，并传递到Servlet2中去查看
        req.setAttribute("key","柜台1的章");

        /**
         *  请求转发必须要以斜杠开头， / 斜杠表示地址为 :http://ip:port/工程名/
         *  映射到IDEA代码的web目录
         */
        //  问路（Servlet2）怎么走
        // RequestDispatcher requestDispatcher = req.getRequestDispatcher("/servlet2");

        //  可以通过请求转发，访问WEB-INF中的 资源
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/1.html");

        //  访问外部资源，不可以
        // RequestDispatcher requestDispatcher = req.getRequestDispatcher("https://www.baidu.com");
        //  转发到 Servlet2
        requestDispatcher.forward(req,resp);


    }
}
