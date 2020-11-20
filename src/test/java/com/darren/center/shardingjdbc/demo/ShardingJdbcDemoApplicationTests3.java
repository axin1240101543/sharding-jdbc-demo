package com.darren.center.shardingjdbc.demo;

import com.darren.center.shardingjdbc.demo.entity.Customer;
import com.darren.center.shardingjdbc.demo.entity.Orders;
import com.darren.center.shardingjdbc.demo.mapper.CustomerMapper;
import com.darren.center.shardingjdbc.demo.mapper.OrdersMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 垂直分库
 *  1、在sharding_sphere_1上创建orders表，在sharding_sphere_2上创建customer表
 *  2、分片规则：将不同的表插入到不同的库中
 */
@SpringBootTest
class ShardingJdbcDemoApplicationTests3 {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private CustomerMapper customerMapper;

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
    public void insertCustomer(){
        for (int i = 1; i <= 10; i++) {
            Customer customer = new Customer();
            customer.setId(i);
            customer.setName("darren" + i);
            customerMapper.insert(customer);
        }
    }

}
