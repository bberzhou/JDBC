package day1;

import Utils.JDBCUtils;
import day1.entity.User;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.Scanner;

/**
 * @description: 演示使用PreparedStatement替换Statement，解决SQL注入的问题
 * @author: bberzhou@gmail.com
 * @date: 6/30/2021
 * Create By Intellij IDEA
 */
public class PreparedStatementSQLTest {
    public static void main(String[] args) throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        // 除了能够解决Statement的拼串、SQL问题之外，preparedStatement 还有哪些好处呢？
        //  1、preparedStatement 能够操作Blob的数据（照片或者视频），而statement做不到
        //  2、preparedStatement可以实现更加高效的批量操作，因为不需要再去校验sql
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String user = scanner.nextLine();
        System.out.println("请输入密码：");
        String password = scanner.nextLine();
//        String sql = "select * from user_table where user = '" + user + "' and password='" + password+"'";

        //  使用这种方式就有可能出现sql注入的问题，下面这个sql语句，就可以绕过见判断，所以需要使用PreparedStatement
//        String sql1 = "select * from user_table where user = '" + user + "' and password='" + password+"' or 1=1" ;


        //  主要就是因为PreparedStatement 预编译过了SQL语句
        String sql1 = "select * from user_table where user=? and password = ?" ;

        User instance = getInstance(User.class, sql1,user, password);
        if (instance != null) {
            System.out.println("登录成功!");
        } else {
            System.out.println("登录失败！");
        }

    }


    public static <T> T getInstance(Class<T> tClass, String sql, Object... args) throws SQLException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            preparedStatement.setObject(i + 1, args[i]);

        }
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        if (resultSet.next()) {
            //  循环遍历结果集元数据

            //  利用泛型来创建运行时类的对象,返回的类型自然就是T类型的
            T t = tClass.newInstance();
            for (int i = 0; i < columnCount; i++) {
                //  获取列数据值
                Object columnValue = resultSet.getObject(i + 1);
                //  利用metaData获取列名
                String columnLabel = metaData.getColumnLabel(i + 1);

                //  给t对象指定的columnName熟悉，赋值为columnValue：通过反射
                //  反射，通过对象获取类的熟悉
//                Field declaredField = t.getClass().getDeclaredField(columnLabel);
                Field declaredField = tClass.getDeclaredField(columnLabel);
                declaredField.setAccessible(true);
                declaredField.set(t, columnValue);
            }

            //  对象赋值完之后，返回该对象
            return t;
        }
        //  关闭资源
        JDBCUtils.closeResource(connection, preparedStatement, resultSet);
        return null;
    }
}
