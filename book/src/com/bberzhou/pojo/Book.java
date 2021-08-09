package com.bberzhou.pojo;

import java.math.BigDecimal;

/**
 * @description: 图书类
 * @author: bberzhou@gmail.com
 * @date: 8/8/2021
 * Create By Intellij IDEA
 */
public class Book {
    private Integer id;     //  图书id
    private String name;        //  图书书名
    private String author;      //  图书作者
    private BigDecimal price;   //  图书价格
    private Integer sales;      //  图书销量
    private Integer stock;      //  图书库存
    //  图书图片有一个默认值
    private String imgPath = "static/img/default.jpg";

    public Book() {
    }

    public Book(Integer id, String name, String author, BigDecimal price, Integer sales, Integer stock, String imgPath) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.sales = sales;
        this.stock = stock;
        //  注意这里imgPath有一个默认值，所以在设置的时候要做一下判断，不能为空且不是空串才能够赋值
        if ("".equals(imgPath) && imgPath != null){
            this.imgPath = imgPath;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", sales=" + sales +
                ", stock=" + stock +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }
}
