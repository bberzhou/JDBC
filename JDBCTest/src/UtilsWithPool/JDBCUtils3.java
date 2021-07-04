package UtilsWithPool;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @description: 使用dbcp数据库连接池技术获取数据库连接
 * @author: bberzhou@gmail.com
 * @date: 7/4/2021
 * Create By Intellij IDEA
 */
public class JDBCUtils3 {

    //  注意！！！，这里不能直接这样写，因为这样写的话，每次在调用连接的时候，都会去新创建一个数据库连接池
    //  所以这里使用静态代码块的方式，随着类的加载而加载，并且只执行一次

    // public static Connection getConnection() throws Exception {
    //     //  采用配置文件的方式
    //     Properties properties = new Properties();
    //
    //     //  方式一：采用系统加载器的方式获取流
    //     // InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("dbcp.properties");
    //
    //     //  方式二：采用类加载器
    //     // InputStream is = DBCPTest.class.getClassLoader().getResourceAsStream("dbcp.properties");
    //
    //     //  方式三：采用输入流
    //     FileInputStream is = new FileInputStream(new File("src/dbcp.properties"));
    //     properties.load(is);
    //     //  创建一个dbcp数据库连接池
    //     BasicDataSource dataSource = BasicDataSourceFactory.createDataSource(properties);
    //     Connection connection = dataSource.getConnection();
    //     System.out.println(connection);
    // }

    //  创建一个静态的DataSource类对象source，这里没有实例化对象
    private static DataSource source;
    //  使用静态代码块
    //  所以这里使用静态代码块的方式，随着类的加载而加载，并且只执行一次，通过静态代码块来对source对象实例化
    static {
        try {
            Properties properties = new Properties();
            //  加载配置文件
            FileInputStream is = new FileInputStream(new File("src/dbcp.properties"));
            properties.load(is);
            source = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws FileNotFoundException, SQLException {
        Connection connection = source.getConnection();
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
