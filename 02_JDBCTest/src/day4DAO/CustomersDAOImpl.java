package day4DAO;

import day4DAO.Bean.Customer;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

/**
 * @description: CustomersDAO的实现类，此类集成基础的BaseDAO ，并实现CustomersDAO接口中的抽象方法
 * @author: bberzhou@gmail.com
 * @date: 7/2/2021
 * Create By Intellij IDEA
 */
public class CustomersDAOImpl extends BaseDAO implements CustomersDAO {
    //  插入数到Customers
    @Override
    public void insert(Connection connection, Customer customer) {
        String sql = "insert into customers(name, email, birth) values(?,?,?) ";
        //  这里可以直接使用getXXX()方法，获取对应的参数
//        String name = customer.getName();
//        String email = customer.getEmail();
//        Date birth = customer.getBirth();
        update(connection, sql, customer.getName(), customer.getEmail(), customer.getBirth());

    }
    //  根据ID删除
    @Override
    public void deleteById(Connection connection, int id) {
        String sql = "delete from customers where id = ?";
        update(connection, sql, id);

    }
    //  根据ID更新
    @Override
    public void updateById(Connection connection, Customer customer) {
        String sql = "update customers set name =?,email =?,birth=? where id =?";
        update(connection, sql, customer.getName(), customer.getEmail(), customer.getBirth(), customer.getId());

    }

    //  查询单个对象
    @Override
    public Customer getCustomerById(Connection connection, int id) {
        String sql = "select id, name, email, birth from customers where id =?";
//        Customer cust = getInstance(connection, Customer.class, sql, id);
//        return cust;
        return getInstance(connection,Customer.class,sql,id);
    }

    //  查询所有的costumers
    @Override
    public List<Customer> getCustomersAll(Connection connection) {
        String sql = "select id, name, email, birth from customers";
        List<Customer> costumersList = getForList(connection, Customer.class, sql);
        return costumersList;
    }

    //  查询表中所有数据条数
    @Override
    public Long getCount(Connection connection) {
        String sql = "select count(*) from customers";
        Long value = getValue(connection, sql);
        return value;

    }

    //  返回最大的Birth
    @Override
    public Date getMaxBirth(Connection connection) {
        String sql = "select max(birth) from customers";
//        Date date = getValue(connection, sql);
//        return date;
        return getValue(connection, sql);
    }
}
