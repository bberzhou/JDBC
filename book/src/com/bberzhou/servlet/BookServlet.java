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
import java.math.BigDecimal;
import java.util.List;

/**
 * @description: 此类主要负责图书相关的业务操作
 * @author: bberzhou@gmail.com
 * @date: 8/9/2021
 * Create By Intellij IDEA
 */
public class BookServlet extends BaseServlet{
    private final BookService bookService = new BookServiceImpl();

    /**
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  1、接收前端传入的book 信息 封装成为一个对象
        String book_name = req.getParameter("book_name");
        String book_price = req.getParameter("book_price");
        String book_author = req.getParameter("book_author");
        String book_sales = req.getParameter("book_sales");
        String book_stock = req.getParameter("book_stock");
        //  上面这部分也可以使用WebUtils里面的方法进行操作，但是注意要属性名相对应
        // Book book1 = WebUtils.copyParamToBean(req.getParameterMap(), new Book());

        Book book = new Book(null,book_name,book_author,new BigDecimal(book_price),Integer.parseInt(book_sales),Integer.parseInt(book_stock),null);
        bookService.addBook(book);

        // 添加完成之后，再调用list方法
        // list(req,resp);
        //  注意直接使用list里面的请求转发会有一个bud就是刷新页面还会继续添加，所以应该使用重定向的方式来解决这个问题

        //  设置响应状态码302，表示重定向，（）
        // resp.setStatus(302);
        // resp.setHeader("Location","http://localhost:8080/servlet_08/response2");

        //  访问工程以外的资源，请求重定向是可以访问工程以外的资源的
        // resp.setHeader("Location","https://www.baidu.com");

        resp.setStatus(302);
        //  注意：这里添加成功之后，跳转应该还需要将添加的显示到页面中，所以就需要再调用一次 list
        //  并且请求转发是一次请求，而重定向是两次请求, 获取工程名req.getContextPath()

        // resp.setHeader("Location",req.getContextPath()+"/manager/bookServlet?action=list");

        //  采用分页之后，点击添加之后应该 action=page，这样才能正常显示出来，注意这里由于分页之后，当添加数据的时候，要正常回显到最后一条数据

        //  获取当前页，从前端获取当前页，如果没有，则默认值为0 因为下面要进行 +1 操作
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),0);
        pageNo += 1;
        System.out.println(req.getParameter("pageNo"));
        resp.setHeader("Location",req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+pageNo);

    }

    /**
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  1、获取要删除book对象的id，并进行类型转换
        String id = req.getParameter("id");
        // int i = Integer.parseInt(id);    // 注意这里的转换，也可以使用WebUtils里面的转换操作，可以设置一下默认值 为 0
        int i = WebUtils.parseInt(id, 0);
        //  2、调用BookService里面的deleteBookById，进行删除操作
        System.out.println("我操作了");
        bookService.deleteBookById(i);
        //  3、删除之后重定向到管理页面，注意这里还是需要更新一下首页的书籍信息，所以还得查询一下，就需要list操作。
        //  这里直接调用list()操作还是会存在重复提交的问题，应该还是使用重定向的方式。

        // list(req,resp);

        //  重定向方式一
        //  使用重定向的方式，到action=list下面
        // resp.setStatus(302);
        // //  这样地址就重定向到 ：http://localhost:8080/book/manager/bookServlet?action=list
        // resp.setHeader("Location",req.getContextPath()+"/manager/bookServlet?action=list");

        //  重定向方式二
        // resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=list");

        //  当使用分页之后，跳转的就是 action=page了，并且删除之后要显示到最后一页的数据,
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

    /**
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  1、获取请求的参数
        String id = req.getParameter("id");
        String name = req.getParameter("book_name");
        System.out.println(name);
        String author = req.getParameter("book_author");
        String price = req.getParameter("book_price");
        String sales = req.getParameter("book_sales");
        String stock = req.getParameter("book_stock");
        //  2、调用更新方法，执行更新操作
        bookService.updateBook(new Book(Integer.parseInt(id),name,author,new BigDecimal(price),Integer.parseInt(sales),Integer.parseInt(stock),null));
        //  3、页面重定向
        // System.out.println("更新了，现在跳转");
        // list(req,resp);

        //  这里使用重定向应该好一些，如果使用请求转发， F5 会重新执行一次
        System.out.println("更新了，现在重定向");
        //  这是未使用分页的时候，action=list，直接查询所有的数据，
        // resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=list");
        //  使用分页之后，如果修改了，要使用action= page 重新查询一次
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

    /**
     *  此方法主要是做点击修改的时候，在book_edit页面中进行表单回显的操作,
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  在 book_manager 页面点击修改之后，应该执行的逻辑操作
        //  1、获取请求的参数图书编号
        System.out.println("我修改了");
        String strID = req.getParameter("id");
        int id = WebUtils.parseInt(strID, 0);


        //  2、调用bookService.queryBookById() 查询图书
        Book book = bookService.queryBookById(id);

        //  3、保存查询到的图书信息到Request域中，
        req.setAttribute("book",book);
        //  4、请求转发到 /pages/manager/book_edit.jsp页面中去
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);

    }

    /**
     *  点击图书管理时，首先应该是调用 list方法，这样先展示一部分操作，才能进行增删改的操作
     *  前端点击图书管理的时候 ， bookServlet？action=list，会首先调用此方法
     *  调用Service里面的查询方法，将查询到的数据设置到 request域中，前端 book_manager.jsp页面再使用JSTL 到request域进行遍历输出
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  1、通过调用BookService 类里面的查询方法查询全部的图书
        List<Book> bookList = bookService.queryBooks();
        // for (Book book:bookList){
        //     System.out.println(book);
        // }
        //  2、把全部图书保存到request 域中
        req.setAttribute("bookList",bookList);

        //  3、请求转发到/pages/manager/book_manager.jsp 页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);


    }

    /**
     *  处理分页功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  1、获取请求的参数 pageNo 和 pageSize，注意这里获取参数的时候，当第一次请求的时候，
        //      用户没有传递时候应该有一个默认值，pageNo = 1,pageSize  = PAGE_SIZE
        // String pageNoStr = req.getParameter("pageNo");
        // int pageNo = Integer.parseInt(pageNoStr);
        // String pageSizeStr = req.getParameter("pageSize");
        // int pageSize = Integer.parseInt(pageSizeStr);

        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //  2、调用BookService.page(pageNo,pageSize)，返回一个Page对象
        Page<Book> page = bookService.page(pageNo, pageSize);

        //  设置后台的请求地址
        page.setUrl("manager/bookServlet?action=page");

        //  3、将返回的Page对象保存到request 域中进行回显，
        req.setAttribute("page",page);
        //  4、请求转发到pages/manager/book_manager.jsp 页面
        System.out.println("查询完了，跳转");
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
