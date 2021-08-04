package com.bberzhou.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @description:
 * @author: bberzhou@gmail.com
 * @date: 8/3/2021
 * Create By Intellij IDEA
 */
public class JdbcUtils {

    private static DataSource dataSource;
    //  使用静态代码块的方式，初始化数据库连接池，因为随着类的加载，静态代码块就会执行
    //  让类加载的时候就为dataSource对象赋值，并创建一个数据库连接池
    static {
        try {
            Properties properties = new Properties();
            //  利用类加载器读取 jdbc.properties 属性的配置文件。
            InputStream resourceAsStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //  从流中加载数据
            properties.load(resourceAsStream);
            //  创建数据库连接池
            dataSource = DruidDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 获取数据库连接池中的连接
     *
     * @return
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();

    }

    /**
     * 关闭连接，放回数据库连接池
     *
     * @param conn
     */
    public static void close(Connection conn) {
        //  先判断以下 conn 对象是否为空,如果不为空就关闭
        if (conn !=null){
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
