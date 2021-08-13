package com.bberzhou.dao.impl;

import com.bberzhou.dao.OrderDao;
import com.bberzhou.pojo.Order;

import java.sql.SQLException;

/**
 * @description:
 * @author: bberzhou@gmail.com
 * @date: 8/13/2021
 * Create By Intellij IDEA
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?)";
        try {
            return update(sql,order.getOrderId(),order.getCreateTime(), order.getPrice(),order.getStatus(),order.getUserId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //  插入失败就返回 -1
        return -1;
    }
}
