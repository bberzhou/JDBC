package com.bberzhou.test;

import com.bberzhou.pojo.User;
import com.bberzhou.service.UserService;
import com.bberzhou.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {

    //  对UserService() 里面的方法进行测试
    UserService userService = new UserServiceImpl();
    @Test
    public void registerUser() {
        userService.registerUser(new User(null,"bkk","666","bkk@163.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null,"admin","admin","null")));
    }

    @Test
    public void existUsername() {
        if (userService.existUsername("admina")){
            System.out.println("用户名已经存在!");
        }else {
            System.out.println("用户名可用");
        }
    }
}