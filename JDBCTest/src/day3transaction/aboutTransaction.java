package day3transaction;

import Utils.JDBCUtils;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @description:
 * @author: bberzhou@gmail.com
 * @date: 7/1/2021
 * Create By Intellij IDEA
 */
public class aboutTransaction {
    /**
     *  数据库事务介绍：
     *      1、事务：一组逻辑操作单元（ 一个或多个DML操作），使数据从一种状态变换到另外一种状态。
     *      2、事务处理（事物操作）：保证所有事务都作为一个工作单元来执行，即使出现了故障，都不能改变这种执行方式。
     *                          当在一个事务中执行多个操作时，要么所有的事务都被提交（commit），这些修改就永久的保存下来；
     *                          要么数据库管理系统将放弃所作的所有修改，整个事务回滚（rollback）到最初状态。不能出现中间状态
     *      3、这是为了确保数据库中数据的一致性
     *      4、数一旦提交，就不可以回滚
     *      5、哪些操作会导致数据的自动提交？
     *          》DDL操作一旦执行，都会自动提交
     *              》可以通过Set autocommit = false 对DDL操作失效
     *
     *          》DML默认情况下，一旦执行，就会自动提交：
     *              >可以通过 set autocommit = false,的方式取消DML操作的自动提交
     *
     *          》默认在关闭连接时，会自动的提交数据
     *
     */



    /*
        针对于数据表user_table来说：
        AA用户给BB用户转账100;
        那么update user_table set balance = balance - 100 where user = "AA"
            update user_table set balance = balance + 100 where user = "BB"


     */
    @Test
    public void testUpdate() throws SQLException, IOException, ClassNotFoundException {
        String sql1 = "update user_table set balance = balance -100 where user = ?";
        update(sql1,"AA");

        //  但是这里可能会出现sql1执行成功，sql2没有执行。网络延迟或者其他的等等

        String sql2 = "update user_table set balance = balance + 100 where user = ?";
        update(sql2,"BB");
        System.out.println("转账成功");

    }


    //  ***********************未考虑数据库事务的情况下的转账操作******************************************
    //  数据库的通用增删改操作,这里不能查询操作
    public void update(String sql,Object ...args) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            //  可变形参，类似数组的操作
            //  获取数据库连接
            connection = JDBCUtils.getConnection();
            //  预编译SQL
            preparedStatement = connection.prepareStatement(sql);
            //  循环填充占位符,注意参数下标从1开始
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1,args[i]);
            }
            //  执行
            preparedStatement.execute();



        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //  关闭资源
            JDBCUtils.closeResource(connection,preparedStatement);
        }
    }


    //  ********************************考虑数据库事务后的转账操作**********************

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


    //  这样写就是把连接作为一个参数传进去，然后只有多个DML操作完成之后，才会最后关闭连接，否则就回滚状态
    @Test
    public void testUpdateWithTransaction() {
        Connection connection = null;
        try {
            //  使用带有事务的，这里需要传入一个连接
            connection = JDBCUtils.getConnection();
            System.out.println(connection.getAutoCommit());
            //  取消自动提交
            connection.setAutoCommit(false);

            String sql1 = "update user_table set balance = balance -100 where user = ?";
            update1(connection,sql1,"AA");

            //  但是这里可能会出现sql1执行成功，sql2没有执行。网络延迟或者其他的等等
            //  这里模拟一下网络异常
            System.out.print(10/0);

            String sql2 = "update user_table set balance = balance + 100 where user = ?";
            update1(connection,sql2,"BB");



            //  等两次SQL执行完之后，再手动提交
            connection.commit();

            //  转成功之后再输出
            System.out.println("转账成功");

        } catch (Exception e) {
            e.printStackTrace();
            //  如果失败的话，就回滚
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            //  最后关闭资源
            //  若此时connection没有被关闭，还可能被重复使用，则需要恢复其自动提交状态
            //  setAutoCommit(true)，尤其是在使用数据库连接池技术时，执行close()方法前，建议恢复自动提交状态
            JDBCUtils.closeResource(connection,null);
        }

    }

}
