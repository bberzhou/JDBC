package com.bberzhou.service;

import com.bberzhou.pojo.Cart;

public interface OrderService {
    /**
     *  生成订单
     * @param cart  购物车
     * @param userId    用户id
     * @return
     */
    public String createOrder(Cart cart, Integer userId);

}
