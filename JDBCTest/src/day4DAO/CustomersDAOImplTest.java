package day4DAO;

import Utils.JDBCUtils;
import day4DAO.Bean.Customer;
import org.junit.Test;
import java.sql.Connection;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description:
 * @author: bberzhou@gmail.com
 * @date: 7/2/2021
 * Create By Intellij IDEA
 */
public class CustomersDAOImplTest {
    CustomersDAOImpl customersDAO = new CustomersDAOImpl();
    @Test
    public void testInsert(){
        Connection connection = null;
        try {
             connection = JDBCUtils.getConnection();
             // 这里可以使用 SimpleDateFormat进行解析 指定的方式进行格式化和解析，调用带参数的构造器
            //  将字符串解析成功为 Date格式的
            String date = "2020-09-21";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date da = simpleDateFormat.parse(date);
            Customer customer = new Customer(2,"kk", "22@qq.com", da);
            customersDAO.insert(connection,customer);
            System.out.println("添加成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(connection,null);
        }
    }
}
