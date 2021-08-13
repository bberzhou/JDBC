package com.bberzhou.pojo;

import java.math.BigDecimal;

import java.util.LinkedHashMap;

import java.util.Map;

/**
 * @description: 购物车对象
 * @author: bberzhou@gmail.com
 * @date: 8/12/2021
 * Create By Intellij IDEA
 */
public class Cart {
    //  总商品数量
    private Integer totalCount;

    //  总商品金额
    private BigDecimal totalPrice;
    /**
       *   购物车商品，是一个List集合，里面全是CartItem, 这里用一个Map集合
     *   key 是商品编号
     *   value 是商品信息
     */
    private Map<Integer, CartItem> items = new LinkedHashMap<Integer,CartItem>();
    public Cart() {
    }

    public Cart(Integer totalCount, BigDecimal totalPrice, Map<Integer, CartItem> items) {
        this.totalCount = totalCount;
        this.totalPrice = totalPrice;
        this.items = items;
    }

    public Integer getTotalCount() {
        totalCount =0;
        //  初始时候为0，通过遍历map 里面的entry 个数，来确定总的个数
        for (Map.Entry<Integer, CartItem> entry:items.entrySet()){
            totalCount += entry.getValue().getCount();  //  获取每个 cartItem的 数量，因为一个CartItem 可能有多本书
        }
        // for (CartItem item :items.values()){
        //     totalCount += item.getCount();
        // }
        return totalCount;
    }


    //  这里的设置不能通过set方法设置
    // public void setTotalCount(Integer totalCount) {
    //     this.totalCount = totalCount;
    // }

    public BigDecimal getTotalPrice() {
        totalPrice = new BigDecimal(0);
        //  初始时候为0，通过遍历map 里面的entry 个数，来确定总的个数
        for (Map.Entry<Integer, CartItem> entry:items.entrySet()){
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }

    // public void setTotalPrice(BigDecimal totalPrice) {
    //     this.totalPrice = totalPrice;
    // }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    // @Override
    // public String toString() {
    //     return "Cart{" +
    //             "totalCount=" + totalCount +
    //             ", totalPrice=" + totalPrice +
    //             ", items=" + items +
    //             '}';
    // }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }

    //  这里不使用数据库的方案，所以就用类方法完成

    /**
     *  添加商品项
     * @param cartItem  传入cartItem
     */
    public void addItem(CartItem cartItem){
        //  先查看购物车中是否已经添加过此商品，如果已经添加，则数据累加，总金额更新，如果没有添加过，直接放到集合中即可。

        //  先使用map根据 id 获取一下
        CartItem item = items.get(cartItem.getId());
        //  判断是否添加过
        if (item == null){
            //  如果为空 ，表明为 未添加过
            items.put(cartItem.getId(),cartItem);       //  key 就是 cartItem id ，value就是 cartItem
        }else {
            //  已经添加过了的情况
            item.setCount(item.getCount()+1);   //  数量+1
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));   //  单价乘 新的 总的数量     更新总的金额
        }


    }

    /**
     *  删除商品项
     * @param id 根据商品id删除
     */
    public void deleteItem(Integer id){

        //  直接按照id 删除
        items.remove(id);

    }
    /**
     *  清空购物车
     */
    public void clear(){
        //  直接调用Map的Clear() 方法
        items.clear();
    }

    /**
     *  修改商品数量
     * @param id
     * @param count
     */
    public void updateCount(Integer id,Integer count){
        //  先查看购物车中是否有此商品，如果有，修改商品数量，更新总的金额
        CartItem cartItem = items.get(id);
        if (cartItem != null){
            //  不为空，就修改商品的数量
            cartItem.setCount(count);
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal((cartItem.getCount()))));
        }

    }
}
