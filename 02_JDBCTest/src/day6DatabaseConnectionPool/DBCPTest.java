package day6DatabaseConnectionPool;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @description: 测试DBCP的数据库连接池技术
 * @author: bberzhou@gmail.com
 * @date: 7/4/2021
 * Create By Intellij IDEA
 */
public class DBCPTest {
    //  方式一：硬编码的方式不推荐
    @Test
    public void testGetConnection1() throws SQLException {
        //  创建DBCP的数据库连接池
        BasicDataSource basicDataSource = new BasicDataSource();
        //  设置基本信息
        basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        basicDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF-8&rewriteBatchedStatements=true");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("123456");

        // 还可以设置其他涉及数据库连接池管理的相关属性

        //  设置数据库连接池中可同时连接的最大的连接数
        basicDataSource.setMaxTotal(5);
        //  设置数据库连接池的初始化连接数量
        basicDataSource.setInitialSize(10);

        Connection connection = basicDataSource.getConnection();
        System.out.println(connection);

    }


    //  方式二：使用配置文件的方式
    @Test
    public void testGetConnection2() throws Exception {
        //  采用配置文件的方式
        Properties properties = new Properties();

        //  方式一：采用系统加载器的方式获取流
        // InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("dbcp.properties");

        //  方式二：采用类加载器
        // InputStream is = DBCPTest.class.getClassLoader().getResourceAsStream("dbcp.properties");

        //  方式三：采用输入流
        FileInputStream is = new FileInputStream(new File("src/dbcp.properties"));
        properties.load(is);
        //  创建一个dbcp数据库连接池
        BasicDataSource dataSource = BasicDataSourceFactory.createDataSource(properties);
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }
}
