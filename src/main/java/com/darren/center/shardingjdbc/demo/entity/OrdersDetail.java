package com.darren.center.shardingjdbc.demo.entity;

/**
 * <h3>sharding-jdbc-demo</h3>
 * <p>订单详情表</p>
 *
 * @author : Darren
 * @date : 2020年11月19日 13:55:57
 **/
public class OrdersDetail {

    private Integer id;

    private String detail;

    private Integer orderId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrdersDetail{" +
                "id=" + id +
                ", detail='" + detail + '\'' +
                ", orderId=" + orderId +
                '}';
    }
}
