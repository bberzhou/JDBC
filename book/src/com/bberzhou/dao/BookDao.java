package com.bberzhou.dao;

import com.bberzhou.pojo.Book;
import com.bberzhou.pojo.Page;

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

    /**
     *  用于分页操作中，查询数据库中总的记录数
     * @return 返回总的记录数
     */
    public int queryForPageTotalCount();

    /**
     *  用于分页查询，
     * @param begin  分页查询的起始索引值
     * @param pageSize  分页查询中的每页大小
     * @return  返回一个book的 list集合
     */
    public  List<Book> queryForItems(int begin, Integer pageSize);

    /**
     *   查找价格区间内的所有数据 总数
     * @param min   价格最小值
     * @param max   价格最大值
     * @return
     */
    public int queryForPageSearchTotalCount(Integer min,Integer max);

    /**
     *  价格搜索时，按照每页的数量和起始值搜索
     * @param begin 起始索引位置
     * @param pageSize  每页数据大小
     * @param min 价格最小值
     * @param max   价格最大值
     * @return
     */
    public List<Book> queryForSearchItems(int begin, int pageSize, int min, int max);
}
