package com.bberzhou.service.impl;

import com.bberzhou.dao.BookDao;
import com.bberzhou.dao.impl.BookDaoImpl;
import com.bberzhou.pojo.Book;
import com.bberzhou.service.BookService;

import java.util.List;

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
}
