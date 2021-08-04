package Java.com.bberzhou.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;

public class ContextServlet1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // servletContext 可以像Map一样存取数据；
        //  方式一：先获取 servletConfig 再获取servletContext对象
        // ServletContext servletContext = getServletConfig().getServletContext();
        //  方法二：获取servletContext，直接调用getServletContext()；返回一个servletContext 对象
        ServletContext servletContext = getServletContext();

        //  打印以下当前servletContext对象
        System.out.println("ContextServlet1的servletContext对象："+servletContext);

        System.out.println("ContextServlet1 存key1数据之前的value值是："+servletContext.getAttribute("key1"));
        //  使用setAttribute() 方法存数据
        servletContext.setAttribute("key1","value1");
        System.out.println("ContextServlet1 存key1数据之后的value值是："+servletContext.getAttribute("key1"));

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
