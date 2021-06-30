package day1.exercise;

import Utils.JDBCUtils;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.*;

/**
 * @description: 课后练习2，对examstudent数据表进行增删改操作
 * @author: bberzhou@gmail.com
 * @date: 6/30/2021
 * Create By Intellij IDEA
 */
public class test1 {
    //  问题1：向examStudent表中添加一条记录
    @Test
    public void testInsert() throws SQLException, IOException, ClassNotFoundException {
        String sql = "Insert into examstudent(Type, IDCard, ExamCard,StudentName,Location,Grade) values(?,?,?,?,?,?)";
        int type = 4;
        String IDCard = "343098989";
        String ExamCard = "343098989";
        String StudentName = "343098989";
        String Location = "343098989";
        int Grade = 650;

        int change = change(sql, type, IDCard, ExamCard, StudentName, Location, Grade);
        if (change >0){
            System.out.println("插入成功！");
        }else {
            System.out.println("插入失败！");
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

    public static void main(String[] args) {

    }

    //  问题2：根据身份证号码或者准考证号码查询学生成绩信息
    public <T> T getInstance(Class<T> tClass,String sql, Object ...args) throws SQLException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            preparedStatement.setObject(i+1,args[i]);

        }
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        if (resultSet.next()){
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
                declaredField.set(t,columnValue);
            }

            //  对象赋值完之后，返回该对象
            return t;
        }
        //  关闭资源
        JDBCUtils.closeResource(connection,preparedStatement,resultSet);
        return null;
    }

}
