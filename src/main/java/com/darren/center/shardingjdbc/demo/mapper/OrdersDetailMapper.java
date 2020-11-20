package com.darren.center.shardingjdbc.demo.mapper;

import com.darren.center.shardingjdbc.demo.entity.OrdersDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <h3>sharding-jdbc-demo</h3>
 * <p></p>
 *
 * @author : Darren
 * @date : 2020年11月19日 13:56:35
 **/
@Mapper
@Repository
public interface OrdersDetailMapper {

    @Insert("insert into orders_detail (id, detail, order_id) values (#{id}, #{detail}, #{orderId})")
    public void insert(OrdersDetail ordersDetail);

}
