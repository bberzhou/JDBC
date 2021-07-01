package day1PreparedStatement;

import Utils.JDBCUtils;
import day1PreparedStatement.entity.Customer;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.*;

/**
 * @description:
 * @author: bberzhou@gmail.com
 * @date: 6/27/2021
 * Create By Intellij IDEA
 */
public class CustomerQuery {
    @Test
    public void testQuery() throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, IllegalAccessException {
        String sql = "select id,name,birth,email from customers where id =?";
        Customer customer = queryForCustomer(sql, 13);
        System.out.println(customer);
        String sql1 = "select id,birth,email from customers where name=?";
        Customer customer1 = queryTest(sql1, "汤唯");
        System.out.println(customer1);
//        Customer{id=4, name='null', email='tangw@sina.com', birth=1986-06-13}
        //  这里因为没有查询name，所以创建对象之后是属性值为Null
    }


    //  针对Customers数据表的通用的查询操作方法，返回类型是Customer对象
    public Customer queryForCustomer(String sql, Object... args) throws SQLException, IOException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //  获取数据库连接
        try {
            connection = JDBCUtils.getConnection();

            //  预编译sql语句
            preparedStatement = connection.prepareStatement(sql);

            //  填充占位符
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            //  执行SQL，返回一个结果集
            resultSet = preparedStatement.executeQuery();
            //  获取结果集里面的元数据
            ResultSetMetaData metaData = resultSet.getMetaData();
            //  可以通过结果集里面的元数据来获取结果集里面的列数
            int columnCount = metaData.getColumnCount();
            if (resultSet.next()) {
                //          //  如果查到有结果，这里就创建对象
                Customer customer = new Customer();
                //  这里循环的次数，就是元数据的列数,
                //  处理结果集中一行数据中的每一个列
                for (int i = 0; i < columnCount; i++) {
                    // 注意列数下标是从1 开始的
                    Object columnValue = resultSet.getObject(i + 1);
                    //  得到一列数据之后，就要想办法赋值给对象的属性，但是不能用带参的构造器，
                    //  因为有可能没有适合的构造器（只有几个参数），所以需要先创建一个对象，然后使用set方法

                    //  下面就需要给customer对象指定的columnValue属性，赋值为value:通过反射的方式
                    //  需要通过列名来使用对应的set方法，因为列名对应着对应类的属性
                    String columnName = metaData.getColumnName(i + 1);

                    //  所以通过反射拿到当前，反射中获取运行时类中指定的属性并赋值的操作
                    Field declaredField = Customer.class.getDeclaredField(columnName);
                    //  因为这个属性有可能是私有的，所以需要先setAccessible()
                    declaredField.setAccessible(true);
                    //  把当前这个对象（customer）的这个属性（declaredField）的值设置为columnValue，
                    declaredField.set(customer, columnValue);
                }
                return customer;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(connection,preparedStatement,resultSet);
        }
        return null;
    }





    //    针对Query的查询操作
    @Test
    public void test1() throws SQLException, IOException, ClassNotFoundException {
        Connection connection = JDBCUtils.getConnection();
        String sql = "select * from customers where id =?";
        //  预编译SQL
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //  填充占位符
        preparedStatement.setObject(1, 1);

        //  执行sql语句，并返回结果集
        ResultSet resultSet = preparedStatement.executeQuery();

        //  处理结果集

        //  首先判断结果集是否为空
        if (resultSet.next()) {
            //  判断结果集的下一条是否有数据，如果有数据就返回true，并且指针下移，
            //  如果返回false，指针不会下移，
//        类似迭代器里面 hasNext() 和next()的结合体，但是，没有返回值

            //  如果不为空，获取当前这条数据的各个字段的值
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String email = resultSet.getString(3);
            Date birth = resultSet.getDate(4);

//        查询出来之后把数据封装成一个Java对象
            Customer customer = new Customer(id, name, email, birth);
            System.out.println(customer);

        }
        // 关闭连接资源
        JDBCUtils.closeResource(connection,preparedStatement,resultSet);

    }


    public Customer queryTest(String sql, Object ...args) throws SQLException, IOException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        //  1、首先获取数据库连接
        Connection connection = JDBCUtils.getConnection();

        // 2、预编译SQL
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        // 3、填充占位符
        for (int i = 0; i < args.length; i++) {
            preparedStatement.setObject(i+1,args[i]);

        }

        // 4、执行sql,返回结果结果集
        ResultSet resultSet = preparedStatement.executeQuery();

        //  5、获取元数据
        ResultSetMetaData metaData = resultSet.getMetaData();

        //  6、获取数据列的长度
        int columnCount = metaData.getColumnCount();

        //  7、循环遍历单行数据
        if (resultSet.next()){
            Customer cust = new Customer();
            for (int i = 0; i <columnCount; i++) {
                //  获取对应列的数据
                Object columnValue = resultSet.getObject(i + 1);
                //  获取列名
//                String columnName = metaData.getColumnName(i + 1);
                String columnLabel = metaData.getColumnLabel(i + 1);

                //  通过反射给对象的对应属性赋值
                Field declaredField = Customer.class.getDeclaredField(columnLabel);
                declaredField.setAccessible(true);
                declaredField.set(cust,columnValue);
            }
            //  遍历完一行数据之后，返回封装的对象
            return cust;
        }
        JDBCUtils.closeResource(connection,preparedStatement,resultSet);
        //  如果上面没有进入到if里面，直接返回null
        return null;
    }
}
