package com.bberzhou.servlet;

import com.bberzhou.pojo.Cart;
import com.bberzhou.pojo.Order;
import com.bberzhou.pojo.User;
import com.bberzhou.service.OrderService;
import com.bberzhou.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 处理创建订单的并进行订单号回显的操作
 * @author: bberzhou@gmail.com
 * @date: 8/13/2021
 * Create By Intellij IDEA
 */
public class OrderServlet extends BaseServlet{
    private final OrderService orderService = new OrderServiceImpl();

    /**
     *  生成订单的操作
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  1 先获取Cart购物车对象, 这个cart 属性是保存在session中的
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //  2 获取User的信息
        User user = (User) req.getSession().getAttribute("user");
        //  判断用户是否登录
        if (user == null){
            //  如果没有登录就请求转发 到登录页面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        //  获取用户的id
        Integer id = user.getId();


        //  3 调用 OrderService.creatOrder(Cart, UserId); 方法 生成订单
        String orderId = orderService.createOrder(cart, id);

        //  4 保存成功之后,将orderId 保存到session域中
        if (orderId != null){
            //  这里使用请求转发是可以的,但是会出现表单重复提交的问题,所以还是需要将orderId 放入到session中,然后重定向

            // req.setAttribute("orderId",orderId);
            // req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req,resp);


            req.getSession().setAttribute("orderId", orderId);
            resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
        }
    }
}
