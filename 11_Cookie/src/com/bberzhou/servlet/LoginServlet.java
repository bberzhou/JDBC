package com.bberzhou.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:
 * @author: bberzhou@gmail.com
 * @date: 8/11/2021
 * Create By Intellij IDEA
 */
public class LoginServlet extends BaseServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  1、获取登录页提交的用户名和密码数据
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //  2、和数据库中的数据进行比对
        //  这里做用户名和密码校验，这里暂时先写死
        if ("kkk".equals(username) && "kk".equals(password)){
            //  登录成功的情况
            //  将用户名保存为Cookie
            Cookie cookie = new Cookie("username",username);
            cookie.setMaxAge(60*60*24*7);       //  当前的这个Cookie一周内有效
            resp.addCookie(cookie);
            System.out.println("登录成功!");

        }else {
            //  登录失败的情况。
            System.out.println("登录失败!");
        }

    }
}
