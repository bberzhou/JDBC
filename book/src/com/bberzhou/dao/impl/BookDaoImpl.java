package com.bberzhou.dao.impl;

import com.bberzhou.dao.BookDao;
import com.bberzhou.pojo.Book;

import java.sql.SQLException;
import java.util.List;

/**
 * @description:
 * @author: bberzhou@gmail.com
 * @date: 8/8/2021
 * Create By Intellij IDEA
 */
public class BookDaoImpl extends BaseDao implements BookDao {
    /**
     *  根据id进行查询，并对img_path设置别名
     * @param id 传入id号
     * @return 查询成功直接返回Book对象，否则返回null；
     */
    @Override
    public Book queryBookById(int id) {
        //  注意这里查的时候, img_path 要设置一下 imgPath别名,解决数据库和JavaBean不一致的问题
        String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath from t_book where `id`=?";
        try {
            return queryForOne(Book.class, sql, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *  根据书名查询
     * @param bookName 传入书名
     * @return  查询成功直接返回Book对象，否则返回null；
     */
    @Override
    public Book queryBookByName(String bookName) {
        String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath from t_book where `name`=?";
        try {
           return queryForOne(Book.class, sql, bookName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *  按照id更新书籍信息
     * @param book 传入一个book对象
     * @return  成功返回一个整数，更新失败返回-1
     */
    @Override
    public int updateBook(Book book) {
        //  注意update语句，还要注意sql语句的问号问题
        String sql = "update t_book set `name`=?,`author`=?,`price`=?,`sales`=?,`stock`=?,`img_path`=? where `id`=?";
        try {
            return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath(),book.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;


    }

    /**
     *  根据id删除书籍信息
     * @param id    传入要删除书籍的id
     * @return  删除成功返回正数，失败返回-1
     */
    @Override
    public int deleteBookById(Integer id) {
        //  注意delete 不能这么写
        // String sql = "delete `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` from t_book where `id`=?";

        String sql = "delete from t_book where `id`=?";
        // String sql = "delete * from t_book where `id`=?";
        try {
            //  删除成功返回一个update值(正数)
            return update(sql, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //  删除不成功返回-1
        return -1;
    }

    /**
     * 添加一个书籍信息
     * @param book 传入一个book对象
     * @return 如果成功插入返回一个正数，如果插入失败返回-1
     */
    @Override
    public int addBook(Book book) {
        //  因为id是自增的可以不需要再传入id
        String sql = "insert into t_book(`name`,`author`,`price`,`sales`,`stock`,`img_path`) values(?,?,?,?,?,?) ";
        int add = 0;
        try {
            //  注意传入的值要对应列
            update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath());
            return add;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath from t_book";
        try {
            //  查询成功直接返回list
            return queryForList(Book.class, sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //  如果没查询到，直接返回Null
        return null;
    }
}
