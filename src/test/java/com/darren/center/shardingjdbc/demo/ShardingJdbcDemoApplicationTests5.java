package com.darren.center.shardingjdbc.demo;

import com.darren.center.shardingjdbc.demo.entity.Person;
import com.darren.center.shardingjdbc.demo.mapper.PersonMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 读写分离
 * ds1为写库，ds2为读库
 */
@SpringBootTest
class ShardingJdbcDemoApplicationTests5 {

    @Autowired
    private PersonMapper personMapper;

    @Test
    public void insert(){
        for (int i = 1; i <= 10; i++) {
            Person person = new Person();
            person.setId((long)i);
            person.setName("person" + i);
            personMapper.insert(person);
        }
    }

    @Test
    public void select(){
        Person result = personMapper.select(1);
        System.out.println(result);
    }

}
