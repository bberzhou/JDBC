package com.bberzhou.servlet;

import com.bberzhou.utils.CookieUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 这个类用来处理Cookie操作
 * @author: bberzhou@gmail.com
 * @date: 8/11/2021
 * Create By Intellij IDEA
 */
public class CookieServlet extends BaseServlet {


    /**
     * 创建一个Cookie的方法
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  1、创建Cookie对象，注意Cookie都是键值对的格式保存的
        Cookie cookie = new Cookie("key1", "value1");

        //  2、通知客户端保存Cookie
        resp.addCookie(cookie);
        // resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().write("Cookie创建成功!");
    }

    /**
     * 使用 request.getCookies() 获取所有得cookie ，这个方法返回一个 cookie数组
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  调用getCookies() 方法获取Cookie 对象的数组
        Cookie[] cookies = req.getCookies();
        // for (Cookie cookie:cookies){
        //     System.out.println("Cookie的名称："+cookie.getName()+", Cookie的值："+cookie.getValue());
        // }


        // Cookie iWantCookie = null;
        // //  遍历找需要的那个cookie
        // for (Cookie cookie : cookies) {
        //     System.out.println("Cookie的名称：" + cookie.getName() + ", Cookie的值：" + cookie.getValue());
        //     if ("key1".equals(cookie.getName())){
        //         iWantCookie = cookie;
        //         break;
        //     }
        // }

        //  使用工具类
        Cookie iWantCookie = CookieUtils.findCookie("key1", cookies);

        //  如果  iWantCookie 不等于 null ，则说明就找到了需要的
        if (iWantCookie != null) {
            System.out.println("找到了需要的Cookie");
        }

    }

    protected void updateCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //-----------------方案一，直接覆盖修改
        // //  1、先创建一个要修改得同名得Cookie对象，并赋新得值
        // Cookie cookie = new Cookie("key1","value1++++");
        //
        // //  2、调用response.addCookie()
        // resp.addCookie(cookie);

        // ----------------- 方案二，先获取对应得 cookie ，再调用Cookie得 setValue方法

        //  1、先查找到需要修改得 Cookie对象
        Cookie[] cookies = req.getCookies();
        Cookie cookie = CookieUtils.findCookie("key1", cookies);
        //  2、重新设置新得Value值
        cookie.setValue("value-----");
        //  3、通知客户端添加Cookie
        resp.addCookie(cookie);


        //    注意事项：---------  Cookie值最好不要设置为中文
    }

    /**
     * Cookie得一个生命控制
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void defaultLife(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie("defaultLife", "defaultLife");
        cookie.setMaxAge(-1);   //  设置存活的时间，这个在浏览器中默认就是session级别的
        resp.addCookie(cookie);
    }

    /**
     * 通过设置  cookie的setMaxAge = 0 来立即删除Cookie，不需要关闭浏览器
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteCookieNow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  1、获取要修改的Cookie，
        Cookie cookie = CookieUtils.findCookie("key1", req.getCookies());

        //  做一个非空的判断
        if (cookie != null) {
            //  2、设置cookie的 MaxAge，设置为 0 就立马删除
            cookie.setMaxAge(0);
            //  3、修改之后还是要调用add 方法
            resp.addCookie(cookie);
        }
    }

    /**
     * 设置 cookie的存活时间，以秒为单位 一个小时 3600秒
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void keepCookieAlive(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  1、获取要修改的Cookie，
        Cookie cookie = CookieUtils.findCookie("key1", req.getCookies());
        if (cookie != null){
            //  2、设置cookie的 MaxAge，设置为 3600秒，一个小时
            cookie.setMaxAge(3600);
            //  3、修改之后还是要调用add 方法
            resp.addCookie(cookie);
        }
    }

    /**
     *  通过Path路径来过滤Cookie
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void CookieWithPath(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie("path1","path1");

        //  getContextPath() =====> 得到工程路径
        //  注意这里创建一个不属于 项目里面的路径的话，那么chrome出于安全考虑，不会显示这个路径
        cookie.setPath(req.getContextPath()+"/abc");       //  =====> /工程路径/abc
        resp.addCookie(cookie);
        //
        resp.getWriter().write("创建了一个带有path 路径的cookie");

    }
}
