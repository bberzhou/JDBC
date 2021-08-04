package day5DAO2;

import day4DAO.Bean.Customer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * @description: 此接口用于规范针对于Customers表的常用操作
 * @author: bberzhou@gmail.com
 * @date: 7/2/2021
 * Create By Intellij IDEA
 */
public interface CustomersDAO {
    //  将customer对象添加到数据库中
    void insert(Connection connection, Customer customer) throws SQLException, IOException, ClassNotFoundException;

    //  按照指定的Id，删除表中的一条记录
    void deleteById(Connection connection, int id) throws SQLException, IOException, ClassNotFoundException;
    //  针对内存中的customer对象，去修改数据表中指定的记录，这里可以省略掉id
//    void updateById(Connection connection,int id,Customer customer);
    //  直接根据customer对象的id属性去修改
    void updateById(Connection connection,Customer customer) throws SQLException, IOException, ClassNotFoundException;

    //  针对指定的id查询得到对应的Customer对象
    Customer getCustomerById(Connection connection, int id);

    //  查询表中所有的记录构成的集合
    List<Customer> getCustomersAll(Connection connection);


    //  特殊的一些查询操作，
    //  返回数据表中的数据的条目数
    Long getCount(Connection connection);

    //  返回数据表中最大的生日
    Date getMaxBirth(Connection connection);

}
