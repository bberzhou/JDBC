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
public class RequestAPIServlet extends HttpServlet {
    // 重写doGet()方法
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  getRequestURI()					获取请求的资源路径
        System.out.println("URI===>"+req.getRequestURI());
        //	getRequestURL()				   获取请求的统一资源定位符（绝对路径）
        System.out.println("URL=====>"+req.getRequestURL());

        //  在IDEA中，使用localhost访问时，得到的客户端ip地址是 ====> 127.0.0.1
        //  在IDEA中，使用127.0.0.1访问时，得到的客户端ip地址是 ====> 127.0.0.1
        //  在IDEA中，使用真实ip访问时，得到的客户端ip地址是 ====> 127.0.0.1
        //	getRemoteHost()					获取客户端的IP地址
        System.out.println("HOST=====>"+req.getRemoteHost());
        //	getHeader()							获取请求头
        System.out.println("请求头 User-Agent =====>"+req.getHeader("User-Agent"));
        // 	getParameter()						获取请求的参数

        // 	getParameterValues()			获取请求的参数（多个值的时候使用）
        //
        // getMethod()							获取请求的方式GET or POST
        System.out.println("请求的方式====》"+req.getMethod());
        // 	setAttribute(key, value)			设置域数据
        //
        // 	getAttribute(key)						获取域数据
        //
        // getRequestDispatcher()			获取请求转发的对象

    }
}
