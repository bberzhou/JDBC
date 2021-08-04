package Java.com.bberzhou.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

/**
 * @description: 实现servlet接口，并实现service方法，处理请求，并响应数据
 * @author: bberzhou@gmail.com
 * @date: 8/2/2021
 * Create By Intellij IDEA
 */
public class HelloServlet implements Servlet {
    public HelloServlet() {
        System.out.println("1 构造器方法");
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("2 init 初始化方法");
        //  可以获取servlet程序的别名servlet-name的值
        System.out.println("HelloServlet程序的别名是："+servletConfig.getServletName());
        //  获取初始化参数，init-param
        System.out.println("初始化参数username的值是："+servletConfig.getInitParameter("url"));
        System.out.println("初始化参数url的值是："+servletConfig.getInitParameter("username"));
        //  获取ServletContext对象
        System.out.println(servletConfig.getServletContext());

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /**
     * service 方法是专门用来处理请求和响应的
     *
     * @param servletRequest
     * @param servletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        //  Servlet 里面对GET和POST请求的分发和处理


        //  处理get 和post 不同的请求，不能直接使用 ServletRequest，需要使用子接口HttpServletRequest
        //  通过强制类型转换，因为 这个子类有String getMethod() 方法，可以获取到请求方式
        //  这里之所以能够强转，是因为service 方法传入的servletRequest 参数本来就是子类(httpServletRequest)的，
        //  然后用父类(ServletRequest)去接收的，所以这一步只是强转为本来的子类
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        //  获取请求方式。
        String method = httpServletRequest.getMethod();
        if ("GET".equals(method)) {
            // System.out.println("GET 请求");
            doGet();
        } else if ("POST".equals(method)) {
            // System.out.println("POST请求");
            doPost();
        }

        System.out.println("3、service ==== Hello  Servlet 被访问了");
    }

    /**
     * 此方法专门做 get 请求的操作
     */
    public void doGet() {
        System.out.println("GET 请求");
    }

    /**
     * 此方法专门做Post请求的操作
     */
    public void doPost() {
        System.out.println("POST请求");
    }


    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("4、destroy 销毁方法");
    }
}
