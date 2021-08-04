package day6DatabaseConnectionPool;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @description: Druid数据库连接池测试
 * @author: bberzhou@gmail.com
 * @date: 7/4/2021
 * Create By Intellij IDEA
 */
public class DruidTest {
    //  方式一：硬编码的方式
    @Test
    public void testGetConnection() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        //  设置4个基本信息
        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF-8&rewriteBatchedStatements=true");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("123456");


        druidDataSource.setMaxActive(5);
        druidDataSource.setInitialSize(3);
        DruidPooledConnection connection = druidDataSource.getConnection();
        System.out.println(connection);
    }

    // 方式二：采用配置文件的方式

    @Test
    public void testGetConnection2() throws Exception {
        Properties properties = new Properties();
        InputStream resourceAsStream = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
        properties.load(resourceAsStream);
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        Connection connection = dataSource.getConnection();
        System.out.println(connection);

    }
}
