package day2BLOB;

import Utils.JDBCUtils;
import day1PreparedStatement.entity.Customer;
import org.junit.Test;

import java.io.*;
import java.sql.*;

/**
 * @description: 测试使用PreparedStatement来操作Blob类型的数据
 * @author: bberzhou@gmail.com
 * @date: 7/1/2021
 * Create By Intellij IDEA
 */
public class BlobTest {

    //  向数据库customers中插入Blob类型的字段
    @Test
    public void testBlob() throws SQLException, IOException, ClassNotFoundException {
        Connection connection = JDBCUtils.getConnection();
        String sql = "insert into customers(name,email,birth,photo) values(?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1,"xxLL");
        preparedStatement.setObject(2,"ddd@QQ.com");
        preparedStatement.setObject(3,"1990-03-09");
        //  使用输入流的方式传入
        FileInputStream fis = new FileInputStream(new File("2.jpeg"));
        preparedStatement.setBlob(4,fis);
        preparedStatement.execute();
        fis.close();
        JDBCUtils.closeResource(connection,preparedStatement);
    }

    //  查询数据表customers中的Blob类型的字段
    @Test
    public void testBlobQuery() throws SQLException, IOException, ClassNotFoundException {
        Connection connection = JDBCUtils.getConnection();
        String sql = "select id, name, email, birth, photo from customers where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1,26);
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        //  对返回结果集的前面四个列名进行封装
        if (resultSet.next()){

            //  这里就不需要循环遍历了
            //  方式一：按照列数
//            int id = resultSet.getInt(1);
//            String name = resultSet.getString(2);
//            String  email = resultSet.getString(3);
//            Date  birth = resultSet.getDate(4);
            //  方式二：按照列名进行获取对应列的值
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            Date birth = resultSet.getDate("birth");
            Customer customer = new Customer(id, name, email, birth);

            System.out.println();

            //  怎么拿图片这种数据
            //  将Blob类型的字段下载下来，以文件的方式保存到本地
            Blob photo = resultSet.getBlob("photo");
            //  数据库是输入流
            InputStream binaryStream = photo.getBinaryStream();
            //  输出流到问价
            FileOutputStream fos = new FileOutputStream("zhu.jpg");
            byte[] buffer = new byte[1024];
            int len;
            while ((len = binaryStream.read(buffer)) != -1){
                fos.write(buffer,0,len);
            }
            fos.close();
        }
        JDBCUtils.closeResource(connection,preparedStatement,resultSet);


    }

}
