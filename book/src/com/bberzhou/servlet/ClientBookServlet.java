package com.bberzhou.servlet;

import com.bberzhou.pojo.Book;
import com.bberzhou.pojo.Page;
import com.bberzhou.service.BookService;
import com.bberzhou.service.impl.BookServiceImpl;
import com.bberzhou.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 这个Servlet负责首页的分页数据操作
 * @author: bberzhou@gmail.com
 * @date: 8/10/2021
 * Create By Intellij IDEA
 */
public class ClientBookServlet extends BaseServlet{
    private final BookService bookService = new BookServiceImpl();
    /**
     *  此方法用作首页访问的时候数据处理分页
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  1、获取请求的参数 pageNo 和 pageSize，注意这里获取参数的时候，当第一次请求的时候，
        //      用户没有传递时候应该有一个默认值，pageNo = 1,pageSize  = PAGE_SIZE
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //  2、调用BookService.page(pageNo,pageSize)，返回一个Page对象
        Page<Book> page = bookService.page(pageNo, pageSize);

        //  设置前端的页面的请求路径
        page.setUrl("client/bookServlet?action=page");


        //  3、将返回的Page对象保存到request 域中进行回显，
        req.setAttribute("page",page);
        //  4、请求转发到pages/client/index.jsp 页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }

    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  1、获取请求参数 pageNo,pageSize,价格 min, 价格max
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"),Page.getPageSize());
        //  价格最小值为0
        int min = WebUtils.parseInt(req.getParameter("min"),0);
        //  设置最大价格为 Integer.MAX_VALUE
        int max = WebUtils.parseInt(req.getParameter("max"),Integer.MAX_VALUE);

        //  2、 调用 BookService.page(pageNo,pageSize) 返回一个Page 对象
        Page<Book> page = bookService.pageByPrice(pageNo,pageSize,min,max);

        //  同时还要把价格区间带上才行
        StringBuilder sb = new StringBuilder("client/bookServlet?action=pageByPrice");
        //  这里判断传入的 min 和max 是否为空，如果为空则不需要带上参数，如果不为空就追加上
        if (req.getParameter("min")!= null){
            sb.append("&min=").append(req.getParameter("min"));
        }
        if (req.getParameter("max") != null){
            sb.append("&max=").append(req.getParameter("max"));
        }


        //  3、设置请求 url ,就是那个Servlet
        page.setUrl(sb.toString());

        //  4、保存Page对象到 request域中
        req.setAttribute("page",page);

        //  5、请求转发到 /pages/client/index.jsp  ---- 即查询出来显示到首页上
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);

    }
}
