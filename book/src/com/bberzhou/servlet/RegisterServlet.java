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
 * @description: 处理用户注册部分的逻辑
 * @author: bberzhou@gmail.com
 * @date: 8/4/2021
 * Create By Intellij IDEA
 */
public class RegisterServlet extends HttpServlet {
    //  创建一个userService 对象，用来操作DAO层的，达到分层的目的，避免Web层(servlet)直接操作DAO持久层
    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  1、获取请求参数，用户名，密码，邮箱，验证码
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        //  2、检查验证码是否正确  ====》暂时没有讲如何生成验证码，所以这里先暂时写死 为abcde
        if ("abcde".equalsIgnoreCase(code)){
            //  3、检查用户名是否可用
            if (userService.existUsername(username)){
                //  userService.existUsername(username) 返回true 表示已经存在不可用
                System.out.println("用户名["+username+"]已存在，不可用！");
                req.setAttribute("msg","用户名不可用！");
                req.setAttribute("username",username);
                req.setAttribute("password",password);
                req.setAttribute("email",email);
                //  不可用的情况下，也继续请求转发跳转到注册页面:regist.jsp
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }else {
                //  用户名可用，调用Service保存到数据库
                userService.registerUser(new User(null,username,password,email));
                //  插入数据之后，跳转到注册成功页面 regist.jsp
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }

        }else {
            //  如果用户名和密码都没问题，验证码错误，就需要对用户输入的信息进行回显
            req.setAttribute("msg","验证码错误！");
            req.setAttribute("username",username);
            req.setAttribute("password",password);
            req.setAttribute("email",email);

            System.out.println("验证码["+code+"]错误");
            //  如果验证码不正确，则跳转到注册页面，这里采用请求转发的方式跳转到注册页面:regist.jsp
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }

    }
}
