package com.bberzhou.test;

import com.bberzhou.pojo.Book;
import com.bberzhou.service.BookService;
import com.bberzhou.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class BookServiceTest {
    private final BookService bookService = new BookServiceImpl();
    @Test
    public void addBook() {
        bookService.addBook(new Book(null,"肖申克救赎","李白",new BigDecimal("88.9"),88,10,"static/img/default.jpg"));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(23);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(23,"肖申克","李白白",new BigDecimal("88.9"),88,10,"static/img/default.jpg"));
    }

    @Test
    public void queryBookById() {
        Book book = bookService.queryBookById(23);
        System.out.println(book);
    }

    @Test
    public void queryBooks() {
        List<Book> books = bookService.queryBooks();
        for (Book book:books){
            System.out.println(book);
        }
    }
}