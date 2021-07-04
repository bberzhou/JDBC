package UtilsWithPool;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @description:
 * @author: bberzhou@gmail.com
 * @date: 7/4/2021
 * Create By Intellij IDEA
 */
public class JDBCUtils4 {

    private static DataSource dataSource;
    //  使用静态代码块的方式，让类加载的时候就为dataSource对象赋值，并创建一个数据库连接池
    static {
        try {
            Properties properties = new Properties();
            //  加载配置文件
            InputStream resourceAsStream = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
            properties.load(resourceAsStream);
            //  给 dataSource 赋值
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        return connection;
    }

    //  关闭连接
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
