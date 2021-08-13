package com.bberzhou.servlet;

import com.bberzhou.pojo.Book;
import com.bberzhou.pojo.Cart;
import com.bberzhou.pojo.CartItem;
import com.bberzhou.service.BookService;
import com.bberzhou.service.impl.BookServiceImpl;
import com.bberzhou.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 处理购物车的相关业务逻辑
 * @author: bberzhou@gmail.com
 * @date: 8/12/2021
 * Create By Intellij IDEA
 */
public class CartServlet extends BaseServlet{
    private static final BookService bookService = new BookServiceImpl();
    /**
     *  加入购物车功能，这里使用Session来模拟，未加入数据库
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */

    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("加入购物车");
        System.out.println("商品编号"+req.getParameter("id"));

        //  1、获取请求的参数 商品编号，
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //  2、调用bokService.queryBookById(id)：Book  得到图书的信息
        Book book = bookService.queryBookById(id);
        //  3、把图书信息，转换成为CartItem商品项
        CartItem item = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //  4、调用Cart.addItem(CartItem); 添加商品项

        //  这里不能每次都new ，因为是将cart保存到Session中的，所以要先判断
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null){
            //  如果为空就重新创建一个，并放入到Session中
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);

        }

        cart.addItem(item);
        System.out.println(cart);

        //  添加之后将 书名 放到session域中，让首页的 刚刚把XXXX加入购物车进行回显
        req.getSession().setAttribute("lastName",item.getName());

        //  5、重定向回到商品列表的页面，注意不是一定在首页，如果是在其他页添加的，就需要回到那一页

        //  注意这里不能直接使用重定向，可以通过Referer 来跳转，点击的这个，这个属性可以获取当前触发事件的请求地址

        // resp.sendRedirect(req.getContextPath());
        resp.sendRedirect(req.getHeader("Referer"));

    }

    /**
     *  更新商品数量的方法
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  获取商品的数量，
        String c = req.getParameter("count");
        int count = WebUtils.parseInt(c, 0);
        //  获取商品的id
        String id = req.getParameter("bookId");
        int bookId = WebUtils.parseInt(id, 0);
        //  获取Cart 对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null){
            //  调用更新的方法
            cart.updateCount(bookId,count);

            //  注意这里不能直接使用重定向，可以通过Referer 来跳转，点击的这个，这个属性可以获取当前触发事件的请求地址
            // resp.sendRedirect(req.getContextPath());
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
    /**
     * 删除购物车中的商品
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //  1、获取请求的参数 商品编号，
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //  2、获取Session 中的Cart 属性
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null){
            //  调用删除方法删除购物车商品项
            cart.deleteItem(id);
            //  重定向回原来购物车的展示页面
            //  3、重定向回到商品列表的页面，注意不是一定在首页，如果是在其他页添加的，就需要回到那一页

            //  注意这里不能直接使用重定向，可以通过Referer 来跳转，点击的这个，这个属性可以获取当前触发事件的请求地址

            // resp.sendRedirect(req.getContextPath());
            resp.sendRedirect(req.getHeader("Referer"));
        }

    }

    /**
     * 清空购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void removeItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null){
            cart.clear();
            //  注意这里不能直接使用重定向，可以通过Referer 来跳转，点击的这个，这个属性可以获取当前触发事件的请求地址
            // resp.sendRedirect(req.getContextPath());
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
}
