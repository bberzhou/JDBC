package com.bberzhou.service;

import com.bberzhou.pojo.Book;
import com.bberzhou.pojo.Page;

import java.util.List;

/**
 * @description: BookService处理图书业务的业务逻辑接口
 * @author: bberzhou@gmail.com
 * @date: 8/9/2021
 * Create By Intellij IDEA
 */
public interface BookService {
    /**
     *  添加图书
     * @param book 传入一个图书对象
     */
    public void addBook(Book book);

    /**
     *  根据id删除图书
     * @param bookId 传入要删除图书的id
     */
    public void deleteBookById(Integer bookId);

    /**
     *  更新图书信息，实际也是按照id进行更新的，id是唯一的主键
     * @param book  传入一个图书对象
     */

    public void updateBook(Book book);

    /**
     *  根据id查询图书信息
     * @param bookId 传入需要查询的图书id
     */
    public Book queryBookById(Integer bookId);

    /**
     *  查询所有图书的
     * @return 返回一个List集合
     */
    public List<Book> queryBooks();

    public Page<Book> page(Integer pageNo, Integer pageSize);

    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
