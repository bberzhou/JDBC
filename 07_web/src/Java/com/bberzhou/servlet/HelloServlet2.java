package Java.com.bberzhou.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @description:
 * @author: bberzhou@gmail.com
 * @date: 8/2/2021
 * Create By Intellij IDEA
 */
public class HelloServlet2 extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        // 如果调用父类的init() 方法，就会出现空指针的异常，因为这里重写了之后，就没有 config对象了
        super.init(config);
        System.out.println("重写了init初始化方法，做了一些");
        //  java.lang.NullPointerException， Java.com.bberzhou.servlet.HelloServlet2.doGet(HelloServlet2.java:39)
    }
    //-----------------------------------

    //  重写 doGet 和doPost 分别处理get 和 post请求

    /**
     * doGet() 在Get请求的时候调用
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // super.doGet(req, resp);
        System.out.println("HelloServlet2 doGet method");
        //  也可以使用ServletConfig()获取servlet相关的一些信息

        //  注意这里的getServletConfig() 方法是从GenericServlet()里面获取到的，
        //  GenericServlet里面持有一个ServletConfig类的引用,private transient ServletConfig config;
        ServletConfig servletConfig = getServletConfig();
        String servletName = servletConfig.getServletName();
        System.out.println(servletName);
        //  注意每个Servlet都会有自己对应的一个 servletConfig() 对象，不能通过一个servletConfig()，去获取其他servlet的配置信息，
        //  只能获取自己的，相当于每个都是独立的
        String username = servletConfig.getInitParameter("username");
        System.out.println("初始化参数username2的值是："+username);
    }

    /**
     *  doPost() 在Post请求的时候调用
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // super.doPost(req, resp);
        System.out.println("HelloServlet2 doPost method");
    }
}
