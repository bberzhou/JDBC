package com.bberzhou.filter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:
 * @author: bberzhou@gmail.com
 * @date: 8/13/2021
 * Create By Intellij IDEA
 */
public class FormServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  设置响应格式
        resp.setContentType("text/html;charset=UTF-8");
        //  获取form表单的输入数据
        Object  username = req.getParameter("username");
        Object password = req.getParameter("password");
        System.out.println(username);
        System.out.println(password);
        //  这里没和数据库交互,做一个简单的判断
        if ("kkk".equals(username) && "kkk".equals(password) ){
            //  如果用户名和密码相同, 就把user信息放到session域中
            req.getSession().setAttribute("user",username);
            resp.getWriter().write("登录成功");
        }else {
            //  登录失败,就请求转发到登录页面
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }
    }
}
