package day2BLOB;

import Utils.JDBCUtils;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @description: 使用PreparedStatement实现批量操作，因为update、delete本身就具有批量操作的效果,这里主要指的批量插入
 * @author: bberzhou@gmail.com
 * @date: 7/1/2021
 * Create By Intellij IDEA
 */
public class InsertBatch {
    /**
     * 使用PreparedStatement实现高效的批量操作，
     * <p>
     * 方式一：时使用Statement
     * Connection conn = JDBCUtils.getConnection();
     * Statement st = conn.createStatement();
     * for (int i=0; i<=20000; i++){
     * String sql = "insert into customers(name) Values('name_'+i+');
     * st.execute(sql);
     * }
     */

    //  批量插入的方式二：使用PreparedStatement
//    @Test
//    public void testBatchInsert() throws SQLException, IOException, ClassNotFoundException {
//        Connection connection = JDBCUtils.getConnection();
//        String sql = "insert into `order` (order_name) values(?) ";
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        for (int i = 0; i <= 2000; i++) {
//            preparedStatement.setObject(1,"name_"+i);
//
//            preparedStatement.execute();
//        }
//        JDBCUtils.closeResource(connection,preparedStatement);
//
//    }
//    @Test
//    public void testStatement() throws SQLException, IOException, ClassNotFoundException {
//        Connection connection = JDBCUtils.getConnection();
//        Statement statement = connection.createStatement();
//        for (int i = 0; i <= 2000; i++) {
//            String sql = "insert into `order` (order_name) values('name"+i+"') ";
//            statement.execute(sql);
//
//        }
//        statement.close();
//        connection.close();
//
//    }
//    @Test
//    public void testBatchDelete() throws SQLException, IOException, ClassNotFoundException {
//        Connection conn = JDBCUtils.getConnection();
//        Statement statement = conn.createStatement();
//        for (int i = 10012; i <=12011; i++) {
//            String sql = "delete from `order` where order_id= "+i;
//            statement.execute(sql);
//
//        }
//        statement.close();
//        conn.close();
//
//    }
    @Test
    public void testDeleteWithPre() throws SQLException, IOException, ClassNotFoundException {
        Connection connection = JDBCUtils.getConnection();
        String sql = "delete  from `order` where order_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 6; i <= 94100 ; i++) {
            preparedStatement.setObject(1,i);
            preparedStatement.execute();
        }
        JDBCUtils.closeResource(connection,preparedStatement);
    }


    //  批量插入的方式三：
    //  1、addBatch()、executeBatch()、clearBatch()
    //  2、MySQL服务器默认是关闭批处理的，我们需要通过一个参数，让MySQL开启批处理的支持 ?rewriteBatchedStatements=true
    //  ,
    @Test
    public void testBatchInsert() throws SQLException, IOException, ClassNotFoundException {
        Connection connection = JDBCUtils.getConnection();
        String sql = "insert into `order` (order_name) values(?) ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        long start = System.currentTimeMillis();
        for (int i = 0; i <= 20000; i++) {
            preparedStatement.setObject(1, "name_" + i);

            //  1、”攒“SQL
            preparedStatement.addBatch();

            if (i % 500 == 0) {
                //  2、批量执行
                preparedStatement.executeBatch();

                //  3、执行完之后清空Batch()
                preparedStatement.clearBatch();
            }

            preparedStatement.execute();
        }

        long end = System.currentTimeMillis();
        System.out.println(end - start);

        JDBCUtils.closeResource(connection, preparedStatement);

    }

    //  批量插入的方式四：设置不允许自动提交数据
    @Test
    public void testBatchInsertWithoutCommit() throws SQLException, IOException, ClassNotFoundException {
        Connection connection = JDBCUtils.getConnection();
        String sql = "insert into `order` (order_name) values(?) ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //  设置不允许自动提交数据
        connection.setAutoCommit(false);
        long s = System.currentTimeMillis();
        for (int i = 0; i <= 20000; i++) {
            preparedStatement.setObject(1, "name_" + i);

            //  1、”攒“SQL
            preparedStatement.addBatch();

            if (i % 500 == 0) {
                //  2、执行
                preparedStatement.executeBatch();

                //  3、执行之后清空Batch()
                preparedStatement.clearBatch();
            }

            preparedStatement.execute();
        }
        //  执行完之后，再一起提交数据
        connection.commit();  //    不设置这个自动提交，20S，设置之后3 S
        long e = System.currentTimeMillis();
        System.out.println(e - s);
        JDBCUtils.closeResource(connection, preparedStatement);

    }

}
