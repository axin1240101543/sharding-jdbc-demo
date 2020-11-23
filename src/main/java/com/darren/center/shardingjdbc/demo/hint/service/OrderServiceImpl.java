package com.darren.center.shardingjdbc.demo.hint.service;

import com.darren.center.shardingjdbc.demo.hint.bean.Address;
import com.darren.center.shardingjdbc.demo.hint.bean.Order;
import com.darren.center.shardingjdbc.demo.hint.bean.OrderItem;
import com.darren.center.shardingjdbc.demo.hint.mapper.AddressMapperImpl;
import com.darren.center.shardingjdbc.demo.hint.mapper.OrderItemMapperImpl;
import com.darren.center.shardingjdbc.demo.hint.mapper.OrderMapperImpl;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements ExampleService{
    private OrderMapperImpl orderMapperImpl;

    private OrderItemMapperImpl orderItemMapperImpl;

    private AddressMapperImpl addressMapperImpl;

    public OrderServiceImpl(final DataSource dataSource) {
        this.orderMapperImpl = new OrderMapperImpl(dataSource);
        this.orderItemMapperImpl = new OrderItemMapperImpl(dataSource);
        this.addressMapperImpl = new AddressMapperImpl(dataSource);
    }

    public OrderServiceImpl( OrderMapperImpl orderMapperImpl,  OrderItemMapperImpl orderItemMapperImpl,  AddressMapperImpl addressMapperImpl) {
        this.orderMapperImpl = orderMapperImpl;
        this.orderItemMapperImpl = orderItemMapperImpl;
        this.addressMapperImpl = addressMapperImpl;
    }

    @Override
    public void initEnvironment() throws SQLException {
        //创建表
        orderMapperImpl.createTableIfNotExists();
        orderItemMapperImpl.createTableIfNotExists();
        //清空表
        orderMapperImpl.truncateTable();
        orderItemMapperImpl.truncateTable();
        //初始化公共表Address
        initAddressTable();
    }

    private void initAddressTable() throws SQLException {
        addressMapperImpl.createTableIfNotExists();
        addressMapperImpl.truncateTable();
        initAddressData();
    }

    private void initAddressData() throws SQLException {
        for (int i = 0; i < 10; i++) {
            insertAddress(i);
        }
    }

    private void insertAddress(final int i) throws SQLException {
        Address address = new Address();
        address.setAddressId((long) i);
        address.setAddressName("address_" + i);
        addressMapperImpl.insert(address);
    }

    @Override
    public void cleanEnvironment() throws SQLException {
        orderMapperImpl.dropTable();
        orderItemMapperImpl.dropTable();
        addressMapperImpl.dropTable();
    }

    @Override
    public void processSuccess() throws SQLException {
        System.out.println("-------------- Process Success Begin ---------------");
        List<Long> orderIds = insertData();
        printData();
        //deleteData(orderIds);
        //printData();
        System.out.println("-------------- Process Success Finish --------------");
    }

    @Override
    public void processFailure() throws SQLException {
        System.out.println("-------------- Process Failure Begin ---------------");
        insertData();
        System.out.println("-------------- Process Failure Finish --------------");
        throw new RuntimeException("Exception occur for transaction test.");
    }

    private List<Long> insertData() throws SQLException {
        System.out.println("---------------------------- Insert Data ----------------------------");
        List<Long> result = new ArrayList<>(10);
        for (int i = 1; i <= 10; i++) {
            Order order = insertOrder(i);
            insertOrderItem(i, order);
            result.add(order.getOrderId());
        }
        return result;
    }

    private Order insertOrder(final int i) throws SQLException {
        Order order = new Order();
        order.setUserId(i);
        order.setAddressId(i);
        order.setStatus("INSERT_TEST");
        orderMapperImpl.insert(order);
        return order;
    }

    private void insertOrderItem(final int i, final Order order) throws SQLException {
        OrderItem item = new OrderItem();
        item.setOrderId(order.getOrderId());
        item.setUserId(i);
        item.setStatus("INSERT_TEST");
        orderItemMapperImpl.insert(item);
    }

    private void deleteData(final List<Long> orderIds) throws SQLException {
        System.out.println("---------------------------- Delete Data ----------------------------");
        for (Long each : orderIds) {
            orderMapperImpl.delete(each);
            orderItemMapperImpl.delete(each);
        }
    }

    @Override
    public void printData() throws SQLException {
        System.out.println("---------------------------- Print Order Data -----------------------");
        for (Object each : orderMapperImpl.selectAll()) {
            System.out.println(each);
        }
        System.out.println("---------------------------- Print OrderItem Data -------------------");
        for (Object each : orderItemMapperImpl.selectAll()) {
            System.out.println(each);
        }
    }
}
