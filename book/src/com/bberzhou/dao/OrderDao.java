package com.bberzhou.dao;

import com.bberzhou.pojo.Order;

public interface OrderDao {
    /**
     *  保存订单的方法
     * @param order 传入一个order对象
     * @return
     */
    public int saveOrder(Order order);

}
