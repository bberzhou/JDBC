package Java.com.bberzhou.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;

public class ContextServlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        // 打印一下 ContextServlet2的servletContext对象
        System.out.println("ContextServlet2的servletContext对象"+servletContext);

        Object key1 = servletContext.getAttribute("key1");
        System.out.println("contextServlet2 中获取域数据key1的值"+key1);

        // contextServlet2 中获取域数据key1的值null，没有访问contextServlet1的时候

        // contextServlet2 中获取域数据key1的值value1 ， 访问了contextServlet1之后，contextServlet2也能正常访问到域数据key1

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
