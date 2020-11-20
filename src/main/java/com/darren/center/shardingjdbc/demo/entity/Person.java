package com.darren.center.shardingjdbc.demo.entity;

/**
 * <h3>sharding-jdbc-demo</h3>
 * <p></p>
 *
 * @author : Darren
 * @date : 2020年11月19日 16:03:27
 **/
public class Person {

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
