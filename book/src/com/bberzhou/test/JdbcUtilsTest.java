package com.bberzhou.test;

import com.bberzhou.utils.JdbcUtils;
import org.junit.Test;

import java.sql.SQLException;

/**
 * @description:
 * @author: bberzhou@gmail.com
 * @date: 8/4/2021
 * Create By Intellij IDEA
 */
public class JdbcUtilsTest {
    @Test
    public void testJdbcUtils(){
        //  注意一个点，数据库连接池里面连接数一般有限制，所以获取连接之后用完了应该及时的释放掉资源
        //  即关闭资源
        try {
            System.out.println(JdbcUtils.getConnection());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
