package com.bberzhou.test;

import com.bberzhou.dao.OrderItemDao;
import com.bberzhou.dao.impl.OrderItemDaoImpl;
import com.bberzhou.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

public class OrderItemDaoTest {

    @Test
    public void saveOrderItem() {
        OrderItemDao orderItemDao = new OrderItemDaoImpl();
        //  注意这里的 orderId 要跟Order表里面对应,不能随便乱写
        orderItemDao.saveOrderItem(new OrderItem(111,"kkk",10,new BigDecimal("23"),new BigDecimal("22"),"1233"));
    }
}