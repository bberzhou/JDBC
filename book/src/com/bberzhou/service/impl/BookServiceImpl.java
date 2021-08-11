package com.bberzhou.service.impl;

import com.bberzhou.dao.BookDao;
import com.bberzhou.dao.impl.BookDaoImpl;
import com.bberzhou.pojo.Book;
import com.bberzhou.pojo.Page;
import com.bberzhou.service.BookService;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: bberzhou@gmail.com
 * @date: 8/9/2021
 * Create By Intellij IDEA
 */
public class BookServiceImpl implements BookService {
    private static final BookDao bookDao = new BookDaoImpl();

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer bookId) {
        bookDao.deleteBookById(bookId);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer bookId) {
       //   将查询到的book 返回
        return bookDao.queryBookById(bookId);

    }

    @Override
    public List<Book> queryBooks() {
        // List<Book> books = bookDao.queryBooks();
        // return books;
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(Integer pageNo, Integer pageSize) {
        //  创建page对象
        Page<Book> page = new Page<>();



        //  2、设置每页显示的数量
        page.setPageSize(pageSize);
        //  3、获取总的记录数
        //  通过bookDao里面的queryForPageTotalCount() 方法求数据库中总的记录数
        int pageTotalCount = bookDao.queryForPageTotalCount();
        //  3、设置总的记录数
        page.setPageTotalCount(pageTotalCount);

        //  总的页数 =  总的记录数/每页的数量就是 向上取整，
        //  方式一：math.ceil (静态方法) 向上取整
        // int pageTotal = (int) Math.ceil(pageTotalCount / pageSize*1.0);
        //  方式二：用if 判断
        // int pageTotal = pageTotalCount / pageSize;
        // if ((pageTotalCount % pageSize) >0){
        //     pageTotal +=1;
        // }
        //  4、求总的页码
        int pageTotal = pageTotalCount % pageSize >0 ?  pageTotalCount / pageSize +1: pageTotalCount / pageSize;
        //  4、设置总的页码值
        page.setPageTotal(pageTotal);

        //  这里还应该对 pageNo做一个简单的校验操作， 数据边界的有效检查，这个是每个分页都必须的，不必写在这个service里面，
        //  应该方法Page类 的 setPage() 方法里面进行判断


        //  1、设置当前页码
        page.setPageNo(pageNo);

        //  求当前页开始的那条数据索引值
        int begin = (page.getPageNo()-1)*pageSize;
        //  5、求当前页的所有数据
        List<Book> items = bookDao.queryForItems(begin,pageSize);
        //  5、设置当前页的所有数据
        page.setItems(items);
        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        //  创建查询结果的page对象
        Page<Book> page = new Page<>();

        //  2、设置每页显示的数量
        page.setPageSize(pageSize);
        //  3、获取总的记录数
        //  通过bookDao里面的queryForPageTotalCount() 方法求数据库中总的记录数
        int pageSearchTotalCount = bookDao.queryForPageSearchTotalCount(min, max);
        //  3、设置总的记录数
        page.setPageTotalCount(pageSearchTotalCount);

        //  总的页数 =  总的记录数/每页的数量就是 向上取整，
        //  方式一：math.ceil (静态方法) 向上取整
        // int pageTotal = (int) Math.ceil(pageTotalCount / pageSize*1.0);
        //  方式二：用if 判断
        // int pageTotal = pageTotalCount / pageSize;
        // if ((pageTotalCount % pageSize) >0){
        //     pageTotal +=1;
        // }
        //  4、求总的页码
        int pageTotal = pageSearchTotalCount % pageSize >0 ?  pageSearchTotalCount / pageSize +1: pageSearchTotalCount / pageSize;
        //  4、设置总的页码值
        page.setPageTotal(pageTotal);

        //  这里还应该对 pageNo做一个简单的校验操作， 数据边界的有效检查，这个是每个分页都必须的，不必写在这个service里面，
        //  应该方法Page类 的 setPage() 方法里面进行判断


        //  1、设置当前页码
        page.setPageNo(pageNo);

        //  求当前页开始的那条数据索引值
        int begin = (page.getPageNo()-1)*pageSize;
        //  5、求当前页的所有数据
        // List<Book> items = bookDao.queryForItems(begin,pageSize);
        List<Book> items = bookDao.queryForSearchItems(begin,pageSize,min,max);

        //  5、设置当前页的所有数据
        page.setItems(items);
        return page;
    }
}
