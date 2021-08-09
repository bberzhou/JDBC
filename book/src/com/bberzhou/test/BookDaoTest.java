package com.bberzhou.test;

import com.bberzhou.dao.BookDao;
import com.bberzhou.dao.impl.BookDaoImpl;
import com.bberzhou.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class BookDaoTest {
    private static final BookDao bookDao = new BookDaoImpl();

    @Test
    public void queryBookById() {
        Book book = bookDao.queryBookById(10);
        System.out.println(book);
    }

    @Test
    public void updateBook() {
        Book book = new Book(21,"肖申克救赎","李白",new BigDecimal(77 ),88,999,null);
        bookDao.updateBook(book);


    }

    @Test
    public void deleteBookById() {
        int i = bookDao.deleteBookById(21);
        System.out.println(i);
    }

    @Test
    public void addBook() {
        Book book = new Book(null,"肖申克救赎","李白",new BigDecimal("88.9"),88,10,"static/img/default.jpg");
        bookDao.addBook(book);
    }

    @Test
    public void queryBook() {
        List<Book> books = bookDao.queryBooks();
        for (Book book:books){
            System.out.println(book);
        }
    }
}