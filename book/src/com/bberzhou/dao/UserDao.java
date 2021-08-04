package com.bberzhou.dao;

import com.bberzhou.pojo.User;

import java.sql.SQLException;

/**
 * @description: UserDao的接口
 * @author: bberzhou@gmail.com
 * @date: 8/4/2021
 * Create By Intellij IDEA
 */
public interface UserDao {

    /**
     *  根据用户名来查询用户信息
     * @param username  传入用户名参数
     * @return  如果返回null，则说明没有这个用户。反之，说明有这个用户
     */
    public User queryUserByUsername(String username);

    /**
     *  保存用户信息到数据库，注册功能
     * @param user 传入一个JavaBean
     * @return  返回-1表示操作失败，其他值表示sql语句影响的行数
     */
    public int saveUser(User user);

    /**
     *  根据用户名和密码进行查询
     * @param username  传入用户名参数
     * @param password  传入用户密码
     * @return  如果返回null，则说明用户名或者密码错误。
     */
    public User queryByUsernameAndPassword(String username, String password);
}
