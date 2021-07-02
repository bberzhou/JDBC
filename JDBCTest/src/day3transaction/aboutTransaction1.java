package day3transaction;

import Utils.JDBCUtils;
import day1PreparedStatement.entity.User;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.*;

/**
 * @description:
 * @author: bberzhou@gmail.com
 * @date: 7/1/2021
 * Create By Intellij IDEA
 */
public class aboutTransaction1 {
    /**
     * 事务的ACID属性：
     * 1、原子性（Atomicity）
     *      原子性是指事务是一个不可分割的工作单位，事务中的操作要么都发生，要么都不发生。
     *
     * 2、一致性（Consistency）
     *      事务必须使数据库从一个一致性状态变换到另外一个一致性状态
     *
     * 3、隔离性（Isolation）
     *      事务的隔离性是指一个事务的执行不能被其他事务干扰，即一个事务内部的操作及使用的数据
     *      对并发的其他事务是隔离的，并发执行的各个事务之间不能互相干扰。
     *
     * 4、持久性（Durability）
     *      持久性是指一个事务一旦被提交，它对数据库中的改变是永久性的，
     *      接下来的其他操作和数据库故障不应该对其有任何影响。
     *
     *
     *  5、事务的四种隔离级别
     *      1、read uncommitted(读未提交数据)
     *      2、read committed(读已提交数据)
     *      3、repeatable read(可重复读)
     *      4、serializable(串行化)
     *
     *      Oracle支持2种事务隔离级别：Read commit ，serializable。默认的事务隔离级别为：read commited
     *      Mysql 支持4种事务隔离级别。Mysql默认的事务隔离级别未：Repeatable Read
     *
     *
     *
     *  6、数据库的并发问题
     *      对于同时运行的多个事务，当这些事务访问数据库中相同的数据时，如果没有采取必要的隔离机制，就会导致各种并发问题
     *      1、脏读：对于两个事务T1， T2，T1读取了已经被T2 更新但是还没有被提交的字段。之后,若T2回滚，T1读取的内容就是临时且无效的。
     *      2、不可重复读：对于两个事务T1，T2 ，T1 读取了一个字段，然后T2更新了该字段。之后，T1再次读取同一个字段，值就不同了
     *      3、幻读：对于两个事务T1，T2， T1 从一个表中读取了一个字段，然后T2在该表中插入了一些新的行。之后，如果T1再次读取同一个表，就会多处几行
     *    数据库事务的隔离性：数据库系统必须具有隔离并发运行各个事务的能力，使它们不会相互影响，避免各种并发问题。
     *    一个事务与其他事务隔离的程度称为隔离级别，数据库规定了多种事务隔离级别，不同的隔离级别对应不同的干扰程度。
     *      隔离级别越高，数据一致性就越好，但并发性越弱。
     *
     *
     *
     */

    //  这里做一个查询
    @Test
    public void testTransactionSelect() throws SQLException, IOException, ClassNotFoundException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        Connection connection = JDBCUtils.getConnection();
        //  这里可以查看当前的隔离级别：
        //  int TRANSACTION_READ_UNCOMMITTED = 1;
        //  int TRANSACTION_READ_COMMITTED   = 2;
        //  int TRANSACTION_REPEATABLE_READ  = 4;
        //  int TRANSACTION_SERIALIZABLE     = 8;
        //  关闭自动提交
        connection.setAutoCommit(false);
        System.out.println(connection.getTransactionIsolation());
        //  设置当前数据库连接的隔离级别
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        System.out.println(connection.getTransactionIsolation());
        String sql = "select user,password,balance from user_table where user = ?";
        User user = getInstance(connection, User.class, sql, "CC");
        System.out.println(user);
    }
    //  这里做一个查询操作
    @Test
    public void testTransactionUpdate() throws SQLException, IOException, ClassNotFoundException, InterruptedException {
        Connection connection = JDBCUtils.getConnection();
        //  取消自动提交
        connection.setAutoCommit(false);

        String sql = "update user_table set balance = ? where user = ?";
        update1(connection,sql,5000,"CC");
        Thread.sleep(15000);
        System.out.println("修改结束");



    }


    //  数据库的通用增删改操作,这里不能查询操作（Version2.0）
    public void update1(Connection connection, String sql,Object ...args) throws SQLException, IOException, ClassNotFoundException {
//        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            //  可变形参，类似数组的操作
            //  因为函数传入一个连接 conn，所以这里就不需要再建了
//            //  获取数据库连接
//            connection = JDBCUtils.getConnection();
            //  1、预编译SQL
            preparedStatement = connection.prepareStatement(sql);
            //  2、循环填充占位符,注意参数下标从1开始
            for (int i = 0; i < args.length; i++) {
                //  小心参数生命错误
                preparedStatement.setObject(i+1,args[i]);
            }
            //  3、执行
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

            //  修改未自动提交数据，主要针对使用数据库连接池的使用
            try {
                connection.setAutoCommit(true);
            }catch (SQLException e){
                e.printStackTrace();
            }
            //  关闭资源
            JDBCUtils.closeResource(null,preparedStatement);
        }
    }


    //  通用的查询操作，用于返回数据表中的一条记录（Version2.0， 考虑上事务）
    public <T> T getInstance(Connection connection, Class<T> tClass,String sql, Object ...args) throws SQLException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException {
//        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            preparedStatement.setObject(i+1,args[i]);

        }
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        if (resultSet.next()){
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
//                Field declaredField = t.getClass().getDeclaredField(columnLabel);
                Field declaredField = tClass.getDeclaredField(columnLabel);
                declaredField.setAccessible(true);
                declaredField.set(t,columnValue);
            }

            //  对象赋值完之后，返回该对象
            return t;
        }
        //  关闭资源
        JDBCUtils.closeResource(null,preparedStatement,resultSet);
        return null;
    }
}
