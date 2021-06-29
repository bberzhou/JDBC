package day1;

import Utils.JDBCUtils;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @description: 使用PreparedStatement
 * @author: bberzhou@gmail.com
 * @date: 6/27/2021
 * Create By Intellij IDEA
 */
public class PreparedStatementUpdateTest {
    //  修改customers表中的一条记录
    @Test
    public void test() throws SQLException, IOException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            //  1、获取数据库的链接
            connection = JDBCUtils.getConnection();

            //  2、预编译SQL语句，返回PreparedStatement的实例
            String sql = "update customers set name=? where id = ?";
            //  3、填充占位符
            preparedStatement = connection.prepareStatement(sql);
            //  4、执行sql
            preparedStatement.setString(1,"吴");
            preparedStatement.setInt(2,18);
            preparedStatement.executeUpdate();

        } finally {
            //  5、关闭资源
            JDBCUtils.closeResource(connection,preparedStatement);

        }
    }
}
