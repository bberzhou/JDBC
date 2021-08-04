package day4DAO;

import Utils.JDBCUtils;
import day4DAO.Bean.Customer;
import jdk.nashorn.internal.scripts.JD;
import org.junit.Test;
import java.sql.Connection;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
    @Test
    public void testDeleteById(){
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            customersDAO.deleteById(conn,29);
            System.out.println("删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(conn,null);
        }
    }
    @Test
    public void testUpdate(){
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            //  更新表中得某一条数据
            String str = "2020-01-09";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = simpleDateFormat.parse(str);
            Customer customer = new Customer(28,"剑","2349@qq.com",date);
            customersDAO.updateById(connection,customer);
            System.out.println("修改成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(connection,null);
        }
    }

    //  查询表中的一条记录
    @Test
    public void testGetCustomerById(){
        Connection connection = null;
        try{
            connection = JDBCUtils.getConnection();
            Customer customerById = customersDAO.getCustomerById(connection, 23);
            System.out.println(customerById);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(connection,null);
        }
    }
    //  查询所有的Customer，
    @Test
    public void testGetCustomersAll(){
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            List<Customer> customersAll = customersDAO.getCustomersAll(connection);
            for (Customer c: customersAll){
                System.out.println(c);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(connection, null);
        }
    }
    @Test
    public void testGetCount(){
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            Long count = customersDAO.getCount(connection);
            System.out.println("costumers表中共有"+count+"条记录。");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(connection, null);
        }

    }
    //  获取Customers表中的最大生日
    @Test
    public void testGetMaxBirth(){
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            Date maxBirth = customersDAO.getMaxBirth(connection);
            System.out.println("Customers表中的最大生日为："+maxBirth);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(connection, null);
        }

    }
}
