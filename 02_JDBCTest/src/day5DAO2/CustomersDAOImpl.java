package day5DAO2;

import day4DAO.Bean.Customer;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

/**
 * @description: CustomersDAO的实现类，此类集成基础的BaseDAO ，并实现CustomersDAO接口中的抽象方法
 * @author: bberzhou@gmail.com
 * @date: 7/2/2021
 * Create By Intellij IDEA
 */
//  CustomersDAOImpl在实现的时候就指明要操作的对象 是Customer类
public class CustomersDAOImpl extends BaseDAO<Customer> implements CustomersDAO {
    //  这里就需要获取当前类CustomersDAOImpl 的父类BaseDAO的泛型 <Customer>
    //  让这个泛型去给父类BaseDAO 里面的 tClass 做一个实例化

    //  就可以在子类里面给父类的 tClass赋值

//    {
//        //  先获取当前类的带泛型的父类，注意这里的this ，并不是指的BaseDAO,应该是当前子类在实例化对象时，会自动执行父类里面的静态代码块
        //  这里写到BaseDAO里面，因为this 可以使每个继承的子类在实例化对象的时候，可以自动的指向当前的子类，不用到每个子类里面都写这一段代码
//        Type genericSuperclass = this.getClass().getGenericSuperclass();
//        //  做一个强转
//        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
//        //  获取了父类的泛型参数  ,这里返回的一个参数数组
//        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
//        tClass = (Class<T>) actualTypeArguments[0];//   泛型的第一个参数，就是当前子类中泛型的具体参数<T>里面的
//    }


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
        return getInstance(connection,sql,id);
    }

    //  查询所有的costumers
    @Override
    public List<Customer> getCustomersAll(Connection connection) {
        String sql = "select id, name, email, birth from customers";
        List<Customer> costumersList = getForList(connection, sql);
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
