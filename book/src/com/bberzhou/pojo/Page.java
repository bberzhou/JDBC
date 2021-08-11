package com.bberzhou.pojo;

import java.util.List;

/**
 * @description:  处理分页的JavaBean，并且还能够根据需要，动态进行变换，所以设置成为一个泛型类，根据当前传入的类进行分页操作
 *                  page是分页的模型对象，  <T>  是具体的模块的Javabean对象
 * @author: bberzhou@gmail.com
 * @date: 8/10/2021
 * Create By Intellij IDEA
 */
public class Page<T> {
    //  默认页面是每页 4个
    public static final Integer PAGE_SIZE = 4;

    //  这里设置每页显示 2条，模拟分页的情况2
    // public static final Integer PAGE_SIZE = 2;
    //  当前页码
    private Integer pageNo;
    //  总的页码
    private Integer pageTotal;
    //  当前页显示的数量
    private Integer pageSize = PAGE_SIZE;
    //  数据库中总的记录数
    private Integer pageTotalCount;
    //  当前页的数据，是一个Book集合
    private List<T> items;


    //  优化功能，抽取分页条
    //  分页条的请求地址
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Page() {
    }

    public Page(Integer pageNo, Integer pageTotal, Integer pageSize, Integer pageTotalCount, List<T> items, String url) {
        this.pageNo = pageNo;
        this.pageTotal = pageTotal;
        this.pageSize = pageSize;
        this.pageTotalCount = pageTotalCount;
        this.items = items;
        this.url = url;
    }

    public static Integer getPageSize() {
        return PAGE_SIZE;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }
    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        if (pageNo <1){
            pageNo = 1;
        }if ( pageNo > pageTotal){
            pageNo = pageTotal;
        }
        this.pageNo = pageNo;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageSize=" + pageSize +
                ", pageTotalCount=" + pageTotalCount +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }
}
