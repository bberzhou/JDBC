package Utils;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @description:
 * @author: bberzhou@gmail.com
 * @date: 6/27/2021
 * Create By Intellij IDEA
 */
public class JDBCUtils {
    public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
        //  1、读取配置文件中4个基本信息
        InputStream resourceAsStream = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(resourceAsStream);
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driverClass = properties.getProperty("DriverClass");

        //  2、加载驱动
        Class.forName(driverClass);

        //  3、获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
        //  4、返回一个connection 对象
        return connection;


    }

    /**
     * 关闭资源的方法
     *
     * @param conn
     * @param ps
     */
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

    //    @Test
//    public void test1() throws IOException, ClassNotFoundException, SQLException {
//        InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
//        Properties properties = new Properties();
//        properties.load(is);
////        System.out.println(properties.getProperty("user"));
//        String user = properties.getProperty("user");
//        String password = properties.getProperty("password");
//        String url = properties.getProperty("url");
//        String driverClass = properties.getProperty("DriverClass");
//
//        Class.forName(driverClass);
//        Connection connection = DriverManager.getConnection(url, user, password);
//        Statement statement = connection.createStatement();
//        String sql = "delete from customers where id=18";
//        statement.execute(sql);
//
//
//    }
    public static void closeResource(Connection conn, PreparedStatement ps,ResultSet resultSet) {
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

}
