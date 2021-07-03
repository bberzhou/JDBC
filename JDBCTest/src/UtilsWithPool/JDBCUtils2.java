package UtilsWithPool;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @description: 使用数据库连接池得JDBCUtils
 * @author: bberzhou@gmail.com
 * @date: 7/3/2021
 * Create By Intellij IDEA
 */
public class JDBCUtils2 {
    //  将创建连接池得操作拿出来，这样不用每次调用getConnection得时候都去创建一个
    //  数据库连接池只需要提供一个就可以了。
   private static final ComboPooledDataSource cpds = new ComboPooledDataSource("helloc3p0");

    public static Connection getConnection(){
        //  导入的自定义的配置名： helloc3p0 在c3p0-config.xml里面的 <named-config name="helloc3p0">
        //  使用c3p0得数据库连接技术；
        Connection connection = null;
        try {
            connection = cpds.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static void closeResource(Connection conn, PreparedStatement ps, ResultSet resultSet) {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(resultSet != null){
                resultSet.close();
            }
        }catch (SQLException e ){
            e.printStackTrace();
        }
    }
    public static void closeResource(Connection conn, PreparedStatement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
