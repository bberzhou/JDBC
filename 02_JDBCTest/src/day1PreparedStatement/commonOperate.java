package day1PreparedStatement;

import Utils.JDBCUtils;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @description:
 * @author: bberzhou@gmail.com
 * @date: 6/27/2021
 * Create By Intellij IDEA
 */
public class commonOperate {
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

    @Test
    public void testCommonOperate() throws SQLException, IOException, ClassNotFoundException {
//        String sql = "delete from customers where id =?";
//        update(sql, 2);

//        注意这里的细节问题，order 表的表名order是关键字，需要加上一个 ``,这样表明order不是关键字了
        String sql = "update  `order` set order_name=? where order_id=?";
        update(sql,"康", "2");
    }
}
