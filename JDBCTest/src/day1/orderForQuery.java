package day1;

import Utils.JDBCUtils;
import day1.entity.Order;
import jdk.nashorn.internal.scripts.JD;
import org.junit.Test;

import java.io.IOException;
import java.sql.*;

/**
 * @description: 针对于Order表的通用查询操作
 * @author: bberzhou@gmail.com
 * @date: 6/29/2021
 * Create By Intellij IDEA
 */
public class orderForQuery {


    @Test
    public void testQuery1() throws SQLException, IOException, ClassNotFoundException {
        Connection connection = JDBCUtils.getConnection();
        String sql = "select * from `order` where order_id =?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1,1);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            int id =(int) resultSet.getObject(1);
            String name = resultSet.getString(2);
            Date date = resultSet.getDate(3);
            Order order = new Order(id, name, date);
            System.out.println(order);

        }
        JDBCUtils.closeResource(connection,preparedStatement);

    }


}
