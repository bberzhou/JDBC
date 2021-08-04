package com.bberzhou.service.impl;

import com.bberzhou.dao.UserDao;
import com.bberzhou.dao.impl.UserDaoImpl;
import com.bberzhou.pojo.User;
import com.bberzhou.service.UserService;

/**
 * @description:
 * @author: bberzhou@gmail.com
 * @date: 8/4/2021
 * Create By Intellij IDEA
 */
public class UserServiceImpl implements UserService {

    //  Service层里面需要和数据库打交道，所以需要创建一个全局的DAO对象，UserDao
    private final UserDao userDao = new UserDaoImpl();
    @Override
    public void registerUser(User user) {
        userDao.saveUser(user);

    }

    @Override
    public User login(User user) {
        return userDao.queryByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    /**
     *  判断用户名是否存在
     * @param username
     * @return 存在返回 true 不存在返回 false
     */
    @Override
    public boolean existUsername(String username) {
        User user = userDao.queryUserByUsername(username);

        // boolean flag = true;
        // if (user == null){
        //     //  如果user 等于 null 表明没查询到，就返回false
        //     flag = false;
        // }else if (username.equals(user.getUsername())){
        //     //  如果 用户名相等就返回true ,表明查询到了
        //     flag = true;
        // }
        // return flag;

        //  如果 user等于null ，表明没查询，返回false，否则返回true
        if (user == null){
            return false;
        }

        return true;
    }
}
