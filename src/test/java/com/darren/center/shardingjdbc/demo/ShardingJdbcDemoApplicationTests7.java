package com.darren.center.shardingjdbc.demo;

import com.darren.center.shardingjdbc.demo.entity.Orders;
import com.darren.center.shardingjdbc.demo.entity.OrdersDetail;
import com.darren.center.shardingjdbc.demo.mapper.OrdersDetailMapper;
import com.darren.center.shardingjdbc.demo.mapper.OrdersMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

/**
 * 绑定表
 *
 * 1、创建sharding_sphere数据库
 * 2、在数据库中创建两张表，orders_1和orders_2
 * 3、在数据库中创建两张表，orders_detail_1和orders_detail_2
 */
@SpringBootTest
class ShardingJdbcDemoApplicationTests7 {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private OrdersDetailMapper ordersDetailMapper;

    @Test
    public void add() {
        for (int i = 1; i <= 10; i++) {
            int i1 = new Random().nextInt(1000);
            Orders orders = new Orders();
            orders.setId(i1);
            orders.setOrderType(i1);
            orders.setCustomerId(i1);
            orders.setAmount(1000.0 * i1);
            ordersMapper.insert(orders);

            OrdersDetail ordersDetail = new OrdersDetail();
            ordersDetail.setId(i1);
            ordersDetail.setDetail("detail" + i1);
            ordersDetail.setOrderId(i1);
            ordersDetailMapper.insert(ordersDetail);
        }
    }

}
