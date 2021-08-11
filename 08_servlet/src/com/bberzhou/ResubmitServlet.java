package com.bberzhou;


import com.google.code.kaptcha.servlet.KaptchaServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @description: 测试表单重复提交的问题
 * @author: bberzhou@gmail.com
 * @date: 8/11/2021
 * Create By Intellij IDEA
 */
public class ResubmitServlet extends HttpServlet {
    /**
     * 使用重定向来解决请求转发的这种表单重复提交问题
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    // @Override
    // protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //     //  获取用户名和密码
    //     String username = req.getParameter("username");
    //     String password = req.getParameter("password");
    //
    //     System.out.println(username);
    //     System.out.println(password);
    //     System.out.println("保存到数据库！！");
    //
    //     //  操作完之后，页面进行转换
    //     req.getRequestDispatcher("/Resubmit2.jsp").forward(req,resp);
    //     /**
    //      *  使用请求转发这种方式，如果按 F5 表单的数据就会重复提交，解决方案就是 使用重定向的方式
    //      */
    //
    //     resp.sendRedirect(req.getContextPath()+"/Resubmit2.jsp");
    // }


    /**
     *  模拟有网络延迟的时候，用户一直点击提交，也会造成重复提交的问题
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    // @Override
    // protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //         //  获取用户名和密码
    //         String username = req.getParameter("username");
    //         String password = req.getParameter("password");
    //
    //         System.out.println(username);
    //         System.out.println(password);
    //         System.out.println("保存到数据库！！");
    //
    //         //  模拟网络延迟，影响跳转操作
    //     // try {
    //     //     Thread.sleep(500);
    //     // } catch (InterruptedException e) {
    //     //     e.printStackTrace();
    //     // }
    //
    //
    //     //  操作完之后，页面进行转换
    //         req.getRequestDispatcher("/Resubmit2.jsp").forward(req,resp);
    // }


    /**
     * 使用谷歌验证码之后的操作
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //  获取Session中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);

        //  使用一次就要马上删除
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        //  获取用户名和密码
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //  获取验证码的值
        String vcode = req.getParameter("Vcode");
        //  比较谷歌生成的验证码和用户输入的
        if (token != null && token.equalsIgnoreCase(vcode)) {
            //  如果验证成功则进行页面的跳转操作
            System.out.println(username);
            System.out.println(password);
            System.out.println(vcode);

            //  进行页面跳转
            resp.sendRedirect(req.getContextPath() + "/Resubmit2.jsp");
        }else {
            System.out.println("请不要重复提交表单");
        }
    }
}
