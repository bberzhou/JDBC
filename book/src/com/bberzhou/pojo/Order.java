package com.bberzhou.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 订单类
 * @author: bberzhou@gmail.com
 * @date: 8/13/2021
 * Create By Intellij IDEA
 */
public class Order {
    //  订单的id
    private String orderId;
    //  订单创建的时间
    private Date  createTime;

    //  订单价格
    private BigDecimal price;

    //  订单状态,默认值为0, 0 表示未发货,1表示已发货,2表示已签收
    private Integer status=0;

    private Integer userId;

    public Order() {
    }

    public Order(String orderId, Date createTime, BigDecimal price, Integer status, Integer userId) {
        this.orderId = orderId;
        this.createTime = createTime;
        this.price = price;
        this.status = status;
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", createTime=" + createTime +
                ", price=" + price +
                ", status=" + status +
                ", userId=" + userId +
                '}';
    }
}
