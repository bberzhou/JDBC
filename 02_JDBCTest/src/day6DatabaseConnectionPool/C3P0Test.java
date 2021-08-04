package day6DatabaseConnectionPool;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @description:
 * @author: bberzhou@gmail.com
 * @date: 7/3/2021
 * Create By Intellij IDEA
 */
public class C3P0Test {
    //  方式一：硬编码得方式
    @Test
    public void testGetConnection() throws PropertyVetoException, SQLException {
        //  获取c3p0数据库连接池
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass( "com.mysql.cj.jdbc.Driver" ); //loads the jdbc driver
        cpds.setJdbcUrl( "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&rewriteBatchedStatements=true" );
        cpds.setUser("root");
        cpds.setPassword("123456");
        //  通过设置相关的参数，对数据库连接池进行管理，在Appendix A: Configuration Properties
        //  设置初始时数据库连接池中的连接数
        cpds.setInitialPoolSize(10);
        //  获取连接
        Connection connection = cpds.getConnection();
        System.out.println(connection);

        //  销毁c3p0数据库连接池
        DataSources.destroy(cpds);
    }


    //  方式二：使用配置文件的方式
    @Test
    public void testConnection2() throws SQLException {
        //  导入的自定义的配置名： helloc3p0 在c3p0-config.xml里面的 <named-config name="helloc3p0">
        ComboPooledDataSource cpds = new ComboPooledDataSource("helloc3p0");
        Connection connection = cpds.getConnection();
        System.out.println(connection);
    }
}
