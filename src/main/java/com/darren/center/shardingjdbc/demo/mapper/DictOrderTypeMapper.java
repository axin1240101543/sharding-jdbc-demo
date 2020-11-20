package com.darren.center.shardingjdbc.demo.mapper;

import com.darren.center.shardingjdbc.demo.entity.DictOrderType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <h3>sharding-jdbc-demo</h3>
 * <p></p>
 *
 * @author : Darren
 * @date : 2020年11月19日 15:22:38
 **/
@Mapper
@Repository
public interface DictOrderTypeMapper {

    @Insert("insert into dict_order_type (order_type) values (#{orderType})")
    public void insert(DictOrderType dictOrderType);

    @Delete("delete from dict_order_type where id = #{id}")
    public int delete(Integer id);

}
