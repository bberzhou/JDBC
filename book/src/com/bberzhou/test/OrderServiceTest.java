package com.bberzhou.test;

import com.bberzhou.pojo.Cart;
import com.bberzhou.pojo.CartItem;
import com.bberzhou.service.OrderService;
import com.bberzhou.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderServiceTest {

    @Test
    public void createOrder() {
        OrderService orderService = new OrderServiceImpl();
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"Java入门",1,new BigDecimal("1000"),new BigDecimal("1000")));
        cart.addItem(new CartItem(1,"Java入门",1,new BigDecimal("1000"),new BigDecimal("1000")));
        cart.addItem(new CartItem(2,"数据结构一算法",1,new BigDecimal("1000"),new BigDecimal("1000")));
        System.out.println("订单号是:"+orderService.createOrder(cart,1));
    }
}