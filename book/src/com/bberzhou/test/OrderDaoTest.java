package com.bberzhou.test;

import com.bberzhou.dao.OrderDao;
import com.bberzhou.dao.impl.OrderDaoImpl;
import com.bberzhou.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class OrderDaoTest {

    @Test
    public void saveOrder() {
        OrderDao orderDao = new OrderDaoImpl();
        Date date = new Date();
        // SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // String format = simpleDateFormat.format(date);
        // System.out.println(format);

        //  由于外键的约束,userId必须 在User表的范围内
        orderDao.saveOrder(new Order("123",date,new BigDecimal("11"),0,111));
    }
}