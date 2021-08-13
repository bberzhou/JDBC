package com.bberzhou.dao.impl;

import com.bberzhou.dao.OrderItemDao;
import com.bberzhou.pojo.OrderItem;

import java.sql.SQLException;

/**
 * @description:
 * @author: bberzhou@gmail.com
 * @date: 8/13/2021
 * Create By Intellij IDEA
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?)";
        try {
            return  update(sql, orderItem.getName(), orderItem.getCount(), orderItem.getPrice(), orderItem.getTotalPrice(), orderItem.getOrderId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //  查询失败 返回 -1
        return  -1;
    }
}
