package com.darren.center.shardingjdbc.demo.entity;

/**
 * <h3>sharding-jdbc-demo</h3>
 * <p>字典表</p>
 *
 * @author : Darren
 * @date : 2020年11月19日 15:20:13
 **/
public class DictOrderType {

    private Integer id;
    private String orderType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    @Override
    public String toString() {
        return "DictOrderType{" +
                "id=" + id +
                ", orderType='" + orderType + '\'' +
                '}';
    }
}
