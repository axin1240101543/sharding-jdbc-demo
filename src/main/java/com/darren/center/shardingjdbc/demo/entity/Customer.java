package com.darren.center.shardingjdbc.demo.entity;

/**
 * <h3>sharding-jdbc-demo</h3>
 * <p>客户表</p>
 *
 * @author : Darren
 * @date : 2020年11月19日 14:45:06
 **/
public class Customer {

    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
