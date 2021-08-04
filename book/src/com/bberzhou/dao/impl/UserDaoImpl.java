package com.bberzhou.dao.impl;

import com.bberzhou.dao.UserDao;
import com.bberzhou.pojo.User;

import java.sql.SQLException;

/**
 * @description:
 * @author: bberzhou@gmail.com
 * @date: 8/4/2021
 * Create By Intellij IDEA
 */
public class UserDaoImpl extends BaseDao implements UserDao {

    @Override
    public User queryUserByUsername(String username) {
        String sql = "select `id`,`username`,`password`,`email` from t_user where username=?";
        try {
            //  如果查询到了，直接返回查询到的对象
            return queryForOne(User.class, sql, username);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //  如果没查询到就返回null
        return null;
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(username,password,email) values(?,?,?) ";
        int update = 0;
        try {
            update = update(sql, user.getUsername(), user.getPassword(), user.getEmail());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return update;
    }

    @Override
    public User queryByUsernameAndPassword(String username, String password) {
        String sql = "select `id`,`username`,`password`,`email` from t_user where username=? and password=?";
        try {
            return queryForOne(User.class, sql, username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
