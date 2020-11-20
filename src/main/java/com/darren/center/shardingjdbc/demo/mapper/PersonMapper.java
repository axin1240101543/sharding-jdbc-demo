package com.darren.center.shardingjdbc.demo.mapper;

import com.darren.center.shardingjdbc.demo.entity.Customer;
import com.darren.center.shardingjdbc.demo.entity.Person;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <h3>sharding-jdbc-demo</h3>
 * <p></p>
 *
 * @author : Darren
 * @date : 2020年11月19日 14:45:24
 **/
@Mapper
@Repository
public interface PersonMapper {

    @Insert("insert into person (id, name) values (#{id}, #{name})")
    public void insert(Person person);


    @Select("select * from person where id = #{id}")
    public Person select(long id);

}
