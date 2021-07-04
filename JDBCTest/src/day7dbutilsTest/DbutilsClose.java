package day7dbutilsTest;

import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @description: DBUtils的Close测试，使用DBUtils工具类里面的提供的关闭方法，实现资源的关闭
 * @author: bberzhou@gmail.com
 * @date: 7/4/2021
 * Create By Intellij IDEA
 */
public class DbutilsClose {

    public static void closeResource(Connection connection, Statement statement, ResultSet resultSet){
        // try {
        //     DbUtils.close(connection);
        //     DbUtils.close(statement);
        //     DbUtils.close(resultSet);
        // } catch (SQLException throwables) {
        //     throwables.printStackTrace();
        // }


        //  自带的关闭的方法
        // DbUtils.closeQuietly(connection);
        // DbUtils.closeQuietly(statement);
        // DbUtils.closeQuietly(resultSet);

        DbUtils.closeQuietly(connection,statement,resultSet);
    }

}
