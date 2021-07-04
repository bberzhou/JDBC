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
 * @description:
 * @author: bberzhou@gmail.com
 * @date: 7/4/2021
 * Create By Intellij IDEA
 */
public class CustomersWithDBCPTest {
    CustomersDAO customersDAO = new CustomersDAOImpl();

    @Test
    public void testInsert(){
        try {
            Connection connection = JDBCUtils2.getConnection();
            String date = "2020-09-21";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date da = simpleDateFormat.parse(date);
            Customer customer = new Customer(2,"kk", "22@qq.com", da);
            customersDAO.insert(connection,customer);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //  查询单个的Customer，在day5DAO2里面的BaseDAO类里面的getForList()方法不需要传入具体的类参数信息
    @Test
    public void testGetCustomerById(){
        Connection connection = null;
        try{
            connection = JDBCUtils3.getConnection();
            Customer customerById = customersDAO.getCustomerById(connection, 22);
            System.out.println(customerById);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils3.closeResource(connection,null);
        }
    }
    //  查询所有的Customer，在day5DAO2里面的BaseDAO类里面的getForList()方法不需要传入具体的类参数信息
    @Test
    public void testGetCustomersAll(){
        Connection connection = null;
        try {
            connection = JDBCUtils3.getConnection();
            List<Customer> customersAll = customersDAO.getCustomersAll(connection);
            for (Customer c: customersAll){
                System.out.println(c);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils3.closeResource(connection,null);
        }
    }
}
