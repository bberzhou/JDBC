package com.bberzhou.dao;

import com.bberzhou.pojo.OrderItem;

/**
 * @description: 保存订单项的接口
 * @author: bberzhou@gmail.com
 * @date: 8/13/2021
 * Create By Intellij IDEA
 */
public interface OrderItemDao {
    /**
     *  保存订单项
     * @param orderItem
     * @return
     */
    public int saveOrderItem(OrderItem orderItem);
}
