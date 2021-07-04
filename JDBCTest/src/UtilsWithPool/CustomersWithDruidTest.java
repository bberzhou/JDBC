package UtilsWithPool;

import day4DAO.Bean.Customer;
import day5DAO2.CustomersDAO;
import day5DAO2.CustomersDAOImpl;
import org.junit.Test;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @description: 测试druid数据库连接池
 * @author: bberzhou@gmail.com
 * @date: 7/4/2021
 * Create By Intellij IDEA
 */
public class CustomersWithDruidTest {
    CustomersDAO customersDAO = new CustomersDAOImpl();

    //  查询单个的Customer，在day5DAO2里面的BaseDAO类里面的getForList()方法不需要传入具体的类参数信息
    @Test
    public void testGetCustomerById(){
        Connection connection = null;
        try{
            connection = JDBCUtils4.getConnection();
            Customer customerById = customersDAO.getCustomerById(connection, 22);
            System.out.println(customerById);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils4.closeResource(connection,null);
        }
    }
    //  查询所有的Customer，在day5DAO2里面的BaseDAO类里面的getForList()方法不需要传入具体的类参数信息
    @Test
    public void testGetCustomersAll(){
        Connection connection = null;
        try {
            connection = JDBCUtils4.getConnection();
            List<Customer> customersAll = customersDAO.getCustomersAll(connection);
            for (Customer c: customersAll){
                System.out.println(c);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils4.closeResource(connection,null);
        }
    }
}
