package day7dbutilsTest;

import UtilsWithPool.JDBCUtils4;
import day4DAO.Bean.Customer;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description: Apache commons-dbutils开源的JDBC工具类库，封装了针对于数据库的增删改查操作
 * @author: bberzhou@gmail.com
 * @date: 7/4/2021
 * Create By Intellij IDEA
 */
public class QueryRunnerTest {
    //  测试使用dbutils插入一条数据
    @Test
    public void testInsert(){
        QueryRunner queryRunner = null;
        Connection conn =null;
        try {
            queryRunner = new QueryRunner();
            conn = JDBCUtils4.getConnection();
            String str = "2021-07-04";
            // SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
            // Date date = simpleDateFormat.parse(str);
            String sql = "insert into customers(name,email,birth) values (?,?,?)";
            queryRunner.update(conn,sql,"剑弟","1309@qq.com",str);
            System.out.println("添加了一条记录！");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //  关闭数据库连接
            JDBCUtils4.closeResource(conn,null);
        }
    }

    //  测试查询单条记录
    //  BeanHandler，是ResultSetHandler接口的实现类，用于封装数据库表中的一条记录
    @Test
    public void testQuery1() throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = JDBCUtils4.getConnection();
        String sql = "select id,name,email,birth from customers where id =?";
        //  BeanHandler，要传入一个 具体要处理的类 Customer.class
        BeanHandler<Customer> customerBeanHandler = new BeanHandler<>(Customer.class);
        Customer customer = queryRunner.query(connection, sql, customerBeanHandler, 23);
        System.out.println(customer);
        JDBCUtils4.closeResource(connection,null);

    }

    //  测试查询多条记录
    //  BeanListHandler，是ResultSetHandler接口的实现类，用于封装数据库表中的一条记录
    @Test
    public void testQuery2() throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = JDBCUtils4.getConnection();
        String sql = "select id,name,email,birth from customers where id < ?";
        //  BeanHandler，要传入一个 具体要处理的类 Customer.class
        BeanListHandler<Customer> customerBeanListHandler = new BeanListHandler<>(Customer.class);
        List<Customer> customerList = queryRunner.query(connection, sql, customerBeanListHandler, 23);
        for (Customer o : customerList){
            System.out.println(o);
        }
        JDBCUtils4.closeResource(connection,null);
    }

    //  测试MapHandler 查询单条记录
    //  MapHandler是ResultSetHandler接口的实现类，对应数据库表中的一条记录
    //  并且将字段以及相对应字段的值作为map中的key和value，
    @Test
    public void testQuery3() throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = JDBCUtils4.getConnection();
        String sql = "select id,name,email,birth from customers where id= ?";
        //  MapHandler  Map<<String,Object>>
        MapHandler mapHandler = new MapHandler();
        Map<String, Object> query = queryRunner.query(connection, sql, mapHandler, 23);
        //  这里返回的时候，就不是对象，而是键值对的形式
        System.out.println(query);
        JDBCUtils4.closeResource(connection,null);
    }

    //  测试MapListHandler 查询单条记录
    //  MapHandler是ResultSetHandler接口的实现类，对应数据库表中的多条记录
    //  并且将字段以及相对应的作为map中的key和value，将map添加到List中
    @Test
    public void testQuery4() throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = JDBCUtils4.getConnection();
        String sql = "select id,name,email,birth from customers where id < ?";
        //  MapHandler  Map<<String,Object>>
        MapListHandler mapListHandler = new MapListHandler();
        //  这里的返回值就是封装在一个list里面的多个Map
        List<Map<String, Object>> query = queryRunner.query(connection, sql, mapListHandler, 23);
        // 遍历list,里面都是Map
        for (Object o : query){
            //  打印每个map
            System.out.println(o);
        }

        JDBCUtils4.closeResource(connection,null);
    }


    //  有一些特殊的值的需求的时候，就可以使用ScalarHandler来进行查询，用于查询特殊值
    //  使用ScalarHandler()，来查询数据库表中的数据条数
    @Test
    public void testQuery5() throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = JDBCUtils4.getConnection();
        String sql = "select count(*) from customers";
        ScalarHandler<Object> objectScalarHandler = new ScalarHandler<>();
        Object query = queryRunner.query(connection, sql, objectScalarHandler);
        System.out.println(query);
        JDBCUtils4.closeResource(connection,null);
    }
    //  使用ScalarHandler()，来查询数据库表中birth最大的值
    @Test
    public void testQuery6() throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = JDBCUtils4.getConnection();
        String sql = "select max(birth) from customers";
        ScalarHandler<Object> objectScalarHandler = new ScalarHandler<>();
        Object query = queryRunner.query(connection, sql, objectScalarHandler);
        System.out.println(query);
        JDBCUtils4.closeResource(connection,null);
    }

    /**
     *  有时候DBUtils提供的不能满足时候，就可以自定义ResultSetHandler的实现类
     * @throws SQLException
     */
    @Test
    public void testQuery7() throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = JDBCUtils4.getConnection();
        String sql = "select id, name,email,birth from customers where id = ?";
        //  自定义实现，用匿名实现类
        ResultSetHandler<Customer> handler = new ResultSetHandler<Customer>() {
            @Override
            //  这个方法的返回值就是query的返回值
            public Customer handle(ResultSet resultSet) throws SQLException {

                //  这里的返回值就是下面query的返回值
                // return null;

                //  利用传入的参数，resultSet
                if (resultSet.next()){
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    Date birth= resultSet.getDate("birth");
                    Customer customer = new Customer(id, name, email, birth);
                    // 如果查询到了，就返回这个对象
                    return customer;
                }
                return null;


            }
        };
        Customer query = queryRunner.query(connection, sql, handler, 23);
        System.out.println(query);
    }
}
