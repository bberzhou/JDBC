package com.bberzhou.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @description: 过滤器的简单演示
 * @author: bberzhou@gmail.com
 * @date: 8/13/2021
 * Create By Intellij IDEA
 */
public class AdminFilter implements Filter {

    public AdminFilter() {
        System.out.println("1. Filter的构造器方法");
    }

    /**
     *  过滤器初始化操作
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("2. Filter的初始化器方法");

        // 1 获取Filter的名称，即filter-name的内容
        System.out.println("filter-name的值是:"+filterConfig.getFilterName());
        //  2 获取在Filter 中配置的init-param 初始化参数
        System.out.println("获取初始化参数username的值是:"+filterConfig.getInitParameter("username"));

        //  3 获取servletContext 对象
        System.out.println("获取servletContext 对象的值是:"+filterConfig.getServletContext());






    }

    /**
     *  此方法专门用于拦截请求,可用于权限检查
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("3. Filter的过滤器   doFilter方法");

        //  因为HttpServletRequest 是 ServletRequest子接口, 子接口下面有具体的实现类
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpServletRequest.getSession();

        //  获取登录信息
        Object user = session.getAttribute("user");

        if (user == null){
            //  如果为空,表明没有登录,则不能访问,就需要请求转发
            servletRequest.getRequestDispatcher("/login.jsp").forward(servletRequest,servletResponse);
            return;
        }else {
            //  如果已经登录了,就让程序继续执行, 让程序继续往下访问用户的目标资源
            /**
             * 这个方法的作用:
             *      1 执行下一个Filter过滤器(如果有)
             *      2 执行目标资源 ( 没有Filter)
             */
            filterChain.doFilter(servletRequest,servletResponse);
        }


    }

    /**
     *  销毁过滤器
     */
    @Override
    public void destroy() {
        System.out.println("4. Filter的销毁 destroy()方法");

    }
}
