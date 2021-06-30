package day1;

import Utils.JDBCUtils;
import day1.entity.Order;
import jdk.nashorn.internal.scripts.JD;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.*;

/**
 * @description: 针对于Order表的通用查询操作
 * @author: bberzhou@gmail.com
 * @date: 6/29/2021
 * Create By Intellij IDEA
 */
public class orderForQuery {
    
       /**
         * @Description: 通用的针对order表的查询方法
         * @Author: bberzhou@gmain.com 
         * @Date: 6/30/2021 
         * @Param: 
         * @return: 
         * @throws:
         */

    /**
     *    针对于数据库表的字段名与类的属性名不相同的情况下：
     *      1、必须声明sql时，使用类的属性名来命名字段的别名
     *      2、在使用ResultSetMetaData的时候，需要使用getColumnLabel()来替换GetColumnName()
     *         获取列的别名，进而可以利用反射来获取对应类的熟悉、
     *      3、如果sql中没有给字段起别名的话,getColumnLabel()获取的就是列名
     *
     *      ORM编程思想（object relational mapping）
     *          一个数据表对应一个Java类
     *          表中的一条数对应Java类的一个对象
     *          表中的一个字段对应Java类中的一个熟悉
      */

    public Order orderfoQuery(String sql, Object ...args) throws SQLException, IOException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Connection connection = JDBCUtils.getConnection();
        //  预编译SQL语句
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //  填充占位符
        for (int i = 0; i < args.length; i++) {
            preparedStatement.setObject(i+1,args[i]);
        }
        //  执行SQL语句
        ResultSet resultSet = preparedStatement.executeQuery();

        //  获取结果集的元数据
        ResultSetMetaData metaData = resultSet.getMetaData();
        if (resultSet.next()){
            //  利用空参构造器 创建一个order对象
            Order order = new Order();
            //  循环遍历每一行里面的列数据 ，metaData.getColumnCount()就是列数
            for (int i = 0; i < metaData.getColumnCount(); i++) {
                //  获取对应列的数据，列值
                Object columnValue = resultSet.getObject(i + 1);
                //  获取对应列的列名:getColumnName() ,不推荐使用
                //  获取对应列的别名：getColumnLabel(), 如果没有起别名的话，就会默认为列名
//                String columnName = metaData.getColumnName(i + 1);

                //  因为这里实体类和数据库里面的表名不一致，所以需要别名
                String columnLabel = metaData.getColumnLabel(i + 1);

                //  利用反射将列名对应的对象进行赋值操作
                Field declaredField = Order.class.getDeclaredField(columnLabel);
                declaredField.setAccessible(true);
                declaredField.set(order, columnValue);
            }

            //  每一行遍历完成之后，返回一个order对象
            return order;
        }
        JDBCUtils.closeResource(connection,preparedStatement,resultSet);
        return null;
    }

    //  测试orderForQuery()
    @Test
    public void testOrderForQuery() throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, IllegalAccessException {
//        String sql = "select * from `order` where order_id =?";
//        Order order = orderfoQuery(sql, 1);
//        System.out.println(order);
        //  这里报错，java.lang.NoSuchFieldException: order_id，由于方法中的列名跟对应的类（order）熟悉不相同

        //  可以通过给SQL语句起别名来解决这个问题,给对应的列起一个别名
        String sql = "select order_id orderId,order_name orderName,order_date orderDate from `order` where order_id =?";
        Order order = orderfoQuery(sql, 1);
        System.out.println(order);

    }





    @Test
    public void testQuery1() throws SQLException, IOException, ClassNotFoundException {
        Connection connection = JDBCUtils.getConnection();
        String sql = "select * from `order` where order_id =?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setObject(1,1);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            int id =(int) resultSet.getObject(1);
            String name = resultSet.getString(2);
            Date date = resultSet.getDate(3);
            Order order = new Order(id, name, date);
            System.out.println(order);

        }
        JDBCUtils.closeResource(connection,preparedStatement);

    }


}
