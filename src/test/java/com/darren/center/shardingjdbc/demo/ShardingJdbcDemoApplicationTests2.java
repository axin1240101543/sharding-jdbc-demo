package com.darren.center.shardingjdbc.demo;

import com.darren.center.shardingjdbc.demo.entity.Orders;
import com.darren.center.shardingjdbc.demo.mapper.OrdersMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

/**
 * 单表垂直+水平切分
 *
 * 1、在不同的数据节点node01,node02上创建不同名称的数据库：sharding_sphere_1,sharding_sphere_2
 * 2、在两个数据库上创建相同的表orders_1,orders_2
 */
@SpringBootTest
class ShardingJdbcDemoApplicationTests2 {

    @Autowired
    private OrdersMapper ordersMapper;

    @Test
    public void addOrdersDB() {
        for (int i = 1; i <= 10; i++) {
            Orders orders = new Orders();
            orders.setId(i);
            orders.setOrderType(i);
            orders.setCustomerId(new Random().nextInt(10));
            orders.setAmount(1000.0 * i);
            ordersMapper.insert(orders);
        }
    }

    @Test
    public void selectOrdersDB(){
        Orders orders = new Orders();
        orders.setId(1);
        orders.setCustomerId(1);
        Orders result = ordersMapper.selectOneDB(orders);
        System.out.println(result);
    }
}
