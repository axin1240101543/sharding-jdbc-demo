package com.darren.center.shardingjdbc.demo.mapper;

import com.darren.center.shardingjdbc.demo.entity.Orders;
import org.apache.ibatis.annotations.*;
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
public interface OrdersMapper {

    @Insert("insert into orders (id, order_type, customer_id, amount) values (#{id}, #{orderType}, #{customerId}, #{amount})")
    public void insert(Orders orders);

    @Select("select * from orders where id = #{id}")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "orderType", column = "order_type"),
        @Result(property = "customerId", column = "customer_id"),
        @Result(property = "customerId", column = "customerId")
    })
    public Orders selectOne(Integer id);

    @Select("select * from orders where id = #{id} and customer_id = #{customerId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "orderType", column = "order_type"),
            @Result(property = "customerId", column = "customer_id"),
            @Result(property = "customerId", column = "customerId")
    })
    public Orders selectOneDB(Orders orders);

}
