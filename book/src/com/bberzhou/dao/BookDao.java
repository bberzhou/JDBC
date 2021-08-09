package com.bberzhou.dao;

import com.bberzhou.pojo.Book;

import java.util.List;

/**
 * @description: Book相关操作的接口
 * @author: bberzhou@gmail.com
 * @date: 8/8/2021
 * Create By Intellij IDEA
 */
public interface BookDao {
    /**
     * 根据书籍id来查询
     * @param id 传入id号
     * @return
     */
    Book queryBookById(int id);
    Book queryBookByName(String bookName);

    /**
     * 更新书籍信息
     * @param book 传入一个book对象
     * @return
     */
    int updateBook(Book book);

    /**
     * 根据id删除书籍
     * @param id    传入要删除书籍的id
     * @return
     */
    int deleteBookById(Integer id);

    /**
     * 添加图书信息
     * @param book  传入一个book对象
     * @return
     */
    public int addBook(Book book);

    /**
     *  多本图书的查询
     * @return  返回一个List集合
     */
    public List<Book> queryBooks();


}
