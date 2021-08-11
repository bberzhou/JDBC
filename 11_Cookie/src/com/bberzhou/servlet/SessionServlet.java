package com.bberzhou.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @description: 这个Servlet用来做Session相关的测试
 * @author: bberzhou@gmail.com
 * @date: 8/11/2021
 * Create By Intellij IDEA
 */
public class SessionServlet extends BaseServlet{

    protected void getSession(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  创建和获取都是这个 getSession() 方法，区别是
        //  第一次调用的时候会创建Session, 以后调用此方法时，都是获取Session
        HttpSession session = req.getSession();
        System.out.println(session.isNew());        //  第一次点击创建Session的时候，就会调用getSession()方法创建一个新的Session
        System.out.println(session.getId());
        /**
         *  第一次点击的时候
         * true
         * 610D08C444F174587734313E4F22C706
         *
         * 后面点击
         * false
         * 610D08C444F174587734313E4F22C706
         */
    }

    /**
     *  往Session中保存数据
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void setSessionAttribute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 往Session里面 设置属性值
        req.getSession().setAttribute("session1","value1");

    }

    /**
     *  从Session中获取数据
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getSessionAttribute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  获取 session 属性
        Object session1 = req.getSession().getAttribute("session1");
        System.out.println(session1);
    }

    /**
     * 获取Session的时长，第一次访问的时候就是默认值，设置了Session存活时长之后就是获取的设置的值
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getSessionLife(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  获取 session 属性的  默认超时时长
        int maxInactiveInterval = req.getSession().getMaxInactiveInterval();
        //  session 的默认存活时长 1800 秒  ==== 30分钟
        System.out.println("Session的默认存活时间："+maxInactiveInterval);
    }

    /**
     *  利用 setMaxInactiveInterval() 设置最大超时时长
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void setSessionLife(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  设置 session 属性的  超时时长，这个方法是单独给一个 session设置，如果要设置全局的就需要在web.xml里面进行配置
        req.getSession().setMaxInactiveInterval(3);
        System.out.println("设置当前session 3s之后超时");
    }
    protected void deleteSessionLife(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //   session 设置 .getMaxInactiveInterval（0） 立即销毁
        // req.getSession().setMaxInactiveInterval(0);
        // System.out.println("立即销毁完成！");


        //  设置Session 立马超时
        HttpSession session = req.getSession();
        //  让session 会话马上超时
        session.invalidate();
        System.out.println("Session已经设置为超时(无效)");
    }
}
