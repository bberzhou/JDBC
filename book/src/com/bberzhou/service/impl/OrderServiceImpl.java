package com.bberzhou.service.impl;

import com.bberzhou.dao.BookDao;
import com.bberzhou.dao.OrderDao;
import com.bberzhou.dao.OrderItemDao;
import com.bberzhou.dao.impl.BookDaoImpl;
import com.bberzhou.dao.impl.OrderDaoImpl;
import com.bberzhou.dao.impl.OrderItemDaoImpl;
import com.bberzhou.pojo.*;
import com.bberzhou.service.OrderService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * @description:
 * @author: bberzhou@gmail.com
 * @date: 8/13/2021
 * Create By Intellij IDEA
 */
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao = new OrderDaoImpl();
    private final OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private final BookDao bookDao = new BookDaoImpl();

    /**
     *  这个Service主要是完成创建订单,保存订单和保存订单项
     */
    @Override
    public String createOrder(Cart cart, Integer userId) {
        //  订单号的生成 -===== 要保证唯一性,要注意,多个用户可能同时创建订单,所以这个事件就不唯一,还需要加上用户id
        String orderId = System.currentTimeMillis()+""+userId;
        //  先保存订单,再保存订单项
        Order order = new Order(orderId,new Date(),new BigDecimal(String.valueOf(cart.getTotalPrice())),0,userId);

        //  保存到订单项
        orderDao.saveOrder(order);


        //  购物车里面的每一条商品项 都是订单项
        //  遍历购物车中的每一个商品项,即订单项,然后把这些订单项保存到数据库中, 因为Items 是一个map, key 是商品编号, value 是商品信息
        for (Map.Entry<Integer, CartItem> entry:cart.getItems().entrySet()){
            //  取出里面的 商品信息
            CartItem cartItem = entry.getValue();
            //  因为orderItem里面的id 是自增的,这里可以不用管, 转换为每一个订单项
            OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getCount(),
                    new BigDecimal(String.valueOf(cartItem.getPrice())),
                    new BigDecimal(String.valueOf(cartItem.getTotalPrice())),orderId);
            //  保存订单项到数据库
            orderItemDao.saveOrderItem(orderItem);


            //  注意这里保存了一个订单项之后, 同时还需要修改前台页面中book 的库存和销量

            //  先调用 bookDao 里面的queryBookById() 方法查询出book
            Book book = bookDao.queryBookById(cartItem.getId());

            //  然后做修改操作
            //  销量做加法
            book.setSales(book.getSales()+cartItem.getCount());
            //  库存做减法
            book.setStock(book.getStock()-cartItem.getCount());

            //  数据库做更新操作
            bookDao.updateBook(book);
        }

        //  保存完成之后就清空购物车里面的内容
        cart.clear();
        //  生成订单,保存成功之后,返回订单号

        return orderId;
    }
}
