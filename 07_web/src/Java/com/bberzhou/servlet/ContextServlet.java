package Java.com.bberzhou.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;

//  ContextServlet的作用
public class ContextServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1、获取web.xml中配置的上下文参数 context-param；
        //  先获取 ServletConfig
        ServletConfig servletConfig = getServletConfig();
        //  创建ServletContext 对象
        ServletContext servletContext = servletConfig.getServletContext();
        String username = servletContext.getInitParameter("username");
        System.out.println("context-param 参数username的值是："+username);
        String password = servletContext.getInitParameter("password");
        System.out.println("context-param 参数password的值是："+password);
        //  注意这里的servletContext 不能获取到web.xml里面 init-param 参数的具体值，只能使用servletConfig 来获取

        //  context-param 参数只能由 servletContext对象 获取，域数据
        //  init-param 参数只能由 servletConfig 对象获取，这个有点儿类似Servlet的局部参数

        // 2、获取当前的工程路径，格式：/工程路径；
        String contextPath = servletContext.getContextPath();
        System.out.println(contextPath);    //  /web_07

        // 3、获取工程部署后在服务器硬盘上的绝对路径；
        /**
         *      /   斜杠被服务器解析地址为  http://ip:port/project name/   映射到IDEA代码的web目录
         */
        String realPath = servletContext.getRealPath("/");
        System.out.println(realPath);

        //  获取工程下的css 目录的绝对路径
        String realCssPath = servletContext.getRealPath("/css");
        System.out.println("工程下css目录的绝对路径就是："+realCssPath);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
