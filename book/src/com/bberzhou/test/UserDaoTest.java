package com.bberzhou.test;

import com.bberzhou.dao.UserDao;
import com.bberzhou.dao.impl.UserDaoImpl;
import com.bberzhou.pojo.User;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class UserDaoTest {
    UserDao userDao = new UserDaoImpl();
    @Test
    public void queryUserByUsername() throws SQLException {

        User admin = userDao.queryUserByUsername("admin");
        System.out.println(admin);
        if (admin == null){
            System.out.println("该用户不存在，用户名可以用！");
        }else {
            System.out.println("用户名已存在！");
        }
    }

    @Test
    public void saveUser() throws SQLException {
        //  插入一个新的用户，因为这个id是自增的，所以不需要传入值
        User user = new User(null, "j", "1234", "123@email");
        int i = userDao.saveUser(user);
        System.out.println(i);
    }

    @Test
    public void queryByUsernameAndPassword() throws SQLException {
        User user = userDao.queryByUsernameAndPassword("admin", "admin");
        if (user == null){
            System.out.println("用户名或密码错误，登录失败");
        }else {
            System.out.println("查询成功");
        }
    }
}