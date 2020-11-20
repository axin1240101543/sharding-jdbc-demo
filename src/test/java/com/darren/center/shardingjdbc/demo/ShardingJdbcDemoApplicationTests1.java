package com.darren.center.shardingjdbc.demo;

import com.darren.center.shardingjdbc.demo.entity.Orders;
import com.darren.center.shardingjdbc.demo.mapper.OrdersMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 单表水平切分
 *
 * 1、创建sharding_sphere数据库
 * 2、在数据库中创建两张表，orders_1和orders_2
 */
@SpringBootTest
class ShardingJdbcDemoApplicationTests1 {

    @Autowired
    private OrdersMapper ordersMapper;

    @Test
    public void addOrders() {
        for (int i = 1; i <= 10; i++) {
            Orders orders = new Orders();
            orders.setId(i);
            orders.setOrderType(i);
            orders.setCustomerId(i);
            orders.setAmount(1000.0 * i);
            ordersMapper.insert(orders);
        }
    }

    @Test
    public void selectOrders(){
        Orders orders = ordersMapper.selectOne(1);
        System.out.println(orders);
    }

}
