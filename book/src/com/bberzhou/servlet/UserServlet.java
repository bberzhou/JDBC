package com.bberzhou.servlet;

import com.bberzhou.pojo.User;
import com.bberzhou.service.UserService;
import com.bberzhou.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @description: 用一个Servlet来处理 登录和注册的请求，对请求的action进行判断
 * @author: bberzhou@gmail.com
 * @date: 8/7/2021
 * Create By Intellij IDEA
 */
public class UserServlet extends HttpServlet {
    // private final UserService userService = new UserServiceImpl();
    //
    // /**
    //  * @param req
    //  * @param resp
    //  * @throws ServletException
    //  * @throws IOException
    //  */
    // protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //     //  1、获取请求的参数
    //     String username = req.getParameter("username");
    //     String password = req.getParameter("password");
    //
    //     //  2、调用userService() 里面的login()方法，
    //     //  login() 方法里面会调用  userDao 里面的queryByUsernameAndPassword()方法，通过username和password两个参数进行查询，
    //     //  如果查询到了，就会返回该对象
    //     User user = userService.login(new User(null, username, password, null));
    //     if (user != null) {
    //         //  不为空，证明有数据，可以登录成功，跳转到登录成功的页面
    //         System.out.println("账号：" + username + "，" + "密码：" + password + " 登录成功！");
    //         req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
    //     } else {
    //         //  登录失败的话，需要把错误信息和回显的表单项信息，保存到request域中
    //         req.setAttribute("msg", "用户名或密码错误");
    //         req.setAttribute("username", username);
    //         //  如果为空，表明登录失败，就跳转到登录页面
    //         System.out.println("账号：" + username + "，" + "密码：" + password + " 登录失败！");
    //         req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
    //
    //     }
    // }
    //
    // /**
    //  * @param req
    //  * @param resp
    //  * @throws ServletException
    //  * @throws IOException
    //  */
    // protected void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //     //  1、获取请求参数，用户名，密码，邮箱，验证码
    //     String username = req.getParameter("username");
    //     String password = req.getParameter("password");
    //     String email = req.getParameter("email");
    //     String code = req.getParameter("code");
    //
    //     //  2、检查验证码是否正确  ====》暂时没有讲如何生成验证码，所以这里先暂时写死 为abcde
    //     if ("abcde".equalsIgnoreCase(code)) {
    //         //  3、检查用户名是否可用
    //         if (userService.existUsername(username)) {
    //             //  userService.existUsername(username) 返回true 表示已经存在不可用
    //             System.out.println("用户名[" + username + "]已存在，不可用！");
    //             req.setAttribute("msg", "用户名不可用！");
    //             req.setAttribute("username", username);
    //             req.setAttribute("password", password);
    //             req.setAttribute("email", email);
    //             //  不可用的情况下，也继续请求转发跳转到注册页面:regist.jsp
    //             req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
    //         } else {
    //             //  用户名可用，调用Service保存到数据库
    //             userService.registerUser(new User(null, username, password, email));
    //             //  插入数据之后，跳转到注册成功页面 regist.jsp
    //             System.out.println("账号：" + username + "，" + "密码：" + password + " 注册成功！");
    //             req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
    //         }
    //
    //     } else {
    //         //  如果用户名和密码都没问题，验证码错误，就需要对用户输入的信息进行回显
    //         req.setAttribute("msg", "验证码错误！");
    //         req.setAttribute("username", username);
    //         req.setAttribute("password", password);
    //         req.setAttribute("email", email);
    //         System.out.println("验证码[" + code + "]错误");
    //         //  如果验证码不正确，则跳转到注册页面，这里采用请求转发的方式跳转到注册页面:regist.jsp
    //         req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
    //
    //     }
    // }
    //
    // @Override
    // protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //     req.setCharacterEncoding("UTF-8");
    //     String action = req.getParameter("action");
    //     if ("login".equals(action)){
    //         login(req,resp);
    //     }else if ("register".equals(action)){
    //         register(req,resp);
    //     }
    // }

}
