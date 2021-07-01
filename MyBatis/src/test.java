import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @description:
 * @author: bberzhou@gmail.com
 * @date: 7/1/2021
 * Create By Intellij IDEA
 */
public class test {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Properties properties = new Properties();
//
//        properties.setProperty("user","root");
//        properties.setProperty("password","123456");
//        properties.setProperty("url","jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&rewriteBatchedStatements=true");
//
//        String user = properties.getProperty("user");
//        String password = properties.getProperty("password");
//        String url = properties.getProperty("url");
//
//        Connection connection = DriverManager.getConnection(url, user, password);
//        System.out.println(connection);

        InputStream resourceAsStream = test.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(resourceAsStream);
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);


    }
}
