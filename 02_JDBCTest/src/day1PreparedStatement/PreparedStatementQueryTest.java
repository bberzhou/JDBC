package day1PreparedStatement;

import Utils.JDBCUtils;
import day1PreparedStatement.entity.Customer;
import day1PreparedStatement.entity.Order;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 使用PreparedStatement针对不同的表的通用的查询操作
 * @author: bberzhou@gmail.com
 * @date: 6/30/2021
 * Create By Intellij IDEA
 */
public class PreparedStatementQueryTest {
    //  返回多个查询结果的情况
    public <T> List<T> getForList(Class<T> tClass, String sql, Object... args) throws SQLException, IOException, ClassNotFoundException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            preparedStatement.setObject(i + 1, args[i]);
        }
        ResultSet resultSet = preparedStatement.executeQuery();

        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        List<T> t = new ArrayList<>();
        while (resultSet.next()){
            // 创建一个运行时类的对象
            T t1 = tClass.newInstance();
            for (int i = 0; i <columnCount; i++) {
                //  获取对应的
                String columnLabel = metaData.getColumnLabel(i+1);
                Object columnValue = resultSet.getObject(i + 1);
                Field declaredField =tClass.getDeclaredField(columnLabel);
//                Field declaredField = t.getClass().getDeclaredField(columnLabel);
                declaredField.setAccessible(true);
                declaredField.set(t1,columnValue);
            }
            //  将查询得到对象添加到list中去
            t.add(t1);
        }

        JDBCUtils.closeResource(connection, preparedStatement, resultSet);

        return t;
    }

    @Test
    public void testGetForList() throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        // 注意不要忘记了别名！！！
        String sql = "SELECT order_id orderId, order_name orderName, order_date orderDate FROM  `order` WHERE order_id<?; ";
        //  查询小于order_id 小于3的信息
        List<Order> forList = getForList(Order.class, sql, 3);
        for (Order o : forList) {
            System.out.println(o);
        }
        String sql1 = "SELECT id,name,email,birth FROM customers WHERE id<? ";
        //  查询小于order_id 小于3的信息，
        List<Customer> forList1 = getForList(Customer.class, sql1, 15);
        for (Customer c : forList1) {
            System.out.println(c);
        }
        //  也可以写lambda表达式
//        forList1.forEach(System.out::println);
    }


    /**
     * @Description: 针对于不同的数据库表的通用的查询操作，返回表中的一条记录
     * @Author: bberzhou@gmain.com
     * @Date: 6/30/2021
     * @Param: Class, sql, para
     * @return: resultSet
     * @throws:
     */
    //  这里涉及到泛型方法，泛型方法就是在调用方法的时候指明泛型的具体类型
    //  T 是返回类型 <T>是向编译器说，这是一个泛型参数,这样就是一个泛型方法了，这里的T是一个类型参数，不是具体的类
    public <T> T getInstance(Class<T> tClass, String sql, Object... args) throws SQLException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            preparedStatement.setObject(i + 1, args[i]);

        }
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        if (resultSet.next()) {
            //  循环遍历结果集元数据

            //  利用泛型来创建运行时类的对象,返回的类型自然就是T类型的
            T t = tClass.newInstance();
            for (int i = 0; i < columnCount; i++) {
                //  获取列数据值
                Object columnValue = resultSet.getObject(i + 1);
                //  利用metaData获取列名
                String columnLabel = metaData.getColumnLabel(i + 1);

                //  给t对象指定的columnName熟悉，赋值为columnValue：通过反射
                //  反射，通过对象获取类的熟悉
                Field declaredField = t.getClass().getDeclaredField(columnLabel);
//                Field declaredField = tClass.getDeclaredField(columnLabel);
                declaredField.setAccessible(true);
                declaredField.set(t, columnValue);
            }

            //  对象赋值完之后，返回该对象
            return t;
        }
        //  关闭资源
        JDBCUtils.closeResource(connection, preparedStatement, resultSet);
        return null;
    }


    @Test
    public void testGetInstance() throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String sql = "select id, name, email from customers where id = ?";
        Customer instance = getInstance(Customer.class, sql, 12);
        System.out.println(instance);
        String sql1 = "select order_id orderId, order_name orderName from `order` where order_id = ?";
        Order order = getInstance(Order.class, sql1, 1);
        System.out.println(order);
    }
}
