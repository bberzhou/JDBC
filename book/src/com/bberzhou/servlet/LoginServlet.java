package com.bberzhou.servlet;

import com.bberzhou.pojo.User;
import com.bberzhou.service.UserService;
import com.bberzhou.service.impl.UserServiceImpl;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:
 * @author: bberzhou@gmail.com
 * @date: 8/4/2021
 * Create By Intellij IDEA
 */
public class LoginServlet extends HttpServlet {
    //  UserService 是一个接口，创建它的实现类
    private final UserService userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  1、获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //  2、调用userService() 里面的login()方法，
        //  login() 方法里面会调用  userDao 里面的queryByUsernameAndPassword()方法，通过username和password两个参数进行查询，
        //  如果查询到了，就会返回该对象
        User user = userService.login(new User(null, username, password, null));
        if (user != null){
            //  不为空，证明有数据，可以登录成功，跳转到登录成功的页面
            System.out.println("账号："+username+"，"+"密码："+password+"登录成功！");
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }else {
            //  登录失败的话，需要把错误信息和回显的表单项信息，保存到request域中
            req.setAttribute("msg","用户名或密码错误");
            req.setAttribute("username",username);
            //  如果为空，表明登录失败，就跳转到登录页面
            System.out.println("账号："+username+"，"+"密码："+password+"登录失败！");
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }

    }
}
