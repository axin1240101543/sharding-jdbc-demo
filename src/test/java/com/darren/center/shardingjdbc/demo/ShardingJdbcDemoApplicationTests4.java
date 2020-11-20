package com.darren.center.shardingjdbc.demo;

import com.darren.center.shardingjdbc.demo.entity.DictOrderType;
import com.darren.center.shardingjdbc.demo.mapper.DictOrderTypeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 公共表
 * 在不同节点的库上创建相同的表
 * 分片规则：公共表表示所有的库都具备相同的表
 */
@SpringBootTest
class ShardingJdbcDemoApplicationTests4 {

    @Autowired
    private DictOrderTypeMapper dictOrderTypeMapper;

    @Test
    public void insert(){
        for (int i = 1; i <= 10; i++) {
            DictOrderType dictOrderType = new DictOrderType();
            dictOrderType.setId(i);
            dictOrderType.setOrderType("dict"+ i);
            dictOrderTypeMapper.insert(dictOrderType);
        }
    }

    @Test
    public void delete(){
        int result = dictOrderTypeMapper.delete(1);
        System.out.println(result);
    }

}
