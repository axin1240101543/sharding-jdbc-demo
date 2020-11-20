package com.darren.center.shardingjdbc.demo;

import com.darren.center.shardingjdbc.demo.entity.DictOrderType;
import com.darren.center.shardingjdbc.demo.mapper.DictOrderTypeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 生产分布式id
 */
@SpringBootTest
class ShardingJdbcDemoApplicationTests6 {

    @Autowired
    private DictOrderTypeMapper dictOrderTypeMapper;

    @Test
    public void insert(){
        for (int i = 1; i <= 20; i++) {
            DictOrderType dictOrderType = new DictOrderType();
            dictOrderType.setOrderType("dict"+ i);
            dictOrderTypeMapper.insert(dictOrderType);
        }
    }

}
