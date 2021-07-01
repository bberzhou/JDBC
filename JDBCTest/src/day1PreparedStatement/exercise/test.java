package day1PreparedStatement.exercise;

import Utils.JDBCUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * @description:  查询一条数据
 * @author: bberzhou@gmail.com
 * @date: 6/30/2021
 * Create By Intellij IDEA
 */
public class test {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        Scanner in = new Scanner(System.in);
        System.out.print("请输入姓名：");
        String name = in.nextLine();
        System.out.print("请输入email：");
        String email = in.nextLine();
        System.out.print("请输入生日：");  // 1992-01-03,会有一个自动的转换操作
        String birth = in.nextLine();
        String sql = "INSERT INTO customers(name, email,birth) VALUES(?,?,?);";
        test test = new test();
        //  通过返回值来判断是否成功操作
        int change = test.change(sql, name, email, birth);
        if (change == 1){
            System.out.println("添加成功！");
        }else {
            System.out.println("添加失败！");
        }

    }

    // 通用的增删改操作
    public  int change(String sql, Object ...args) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            preparedStatement.setObject(i+1, args[i]);
        }
        //  方式一：
        //  执行sql语句，返回一个布尔值
        //  true if the first result is a ResultSet object;
        //  false if the first result is an update count or there is no result
        //  如果是查询操作返回了结果集就是true，如果增删改操作或者没有返回就是false
//        boolean execute = preparedStatement.execute();

        //  方式二：
        //  这里还可以使用另外一个方法
        //  either (1) the row count for SQL Data Manipulation Language (DML),影响行数，增删改操作
        //  statements or (2) 0 for SQL statements that return nothing ，或者是0，没有任何的返回值
        int i = preparedStatement.executeUpdate();
        //  关闭资源
        JDBCUtils.closeResource(connection,preparedStatement);
        return i;
    }
}
