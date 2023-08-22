package com.li.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.li.mybatisplus.dao.UserMapper;
import com.li.mybatisplus.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: lh
 * Date: 2023/8/21 15:57
 * Version: v1.0.0
 * Description: 新增
 **/
@SpringBootTest
public class DeleteTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void deleteTest() {
        int i = userMapper.deleteById(1693542516752244737L);
        System.out.println("影响记录数：" + i);
    }

    @Test
    void deleteTest1() {
        Map<String, Object> map = new HashMap<>() {
            {
                put("name", "习近平");
            }
        };
        int i = userMapper.deleteByMap(map);
        System.out.println("影响记录数：" + i);
    }

    @Test
    void deleteTest2() {
        int i = userMapper.deleteBatchIds(List.of(1094592041087729666L));
        System.out.println("影响记录数：" + i);
    }

    @Test
    void deleteTest3() {
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(User::getName, "刘红雨");
        int i = userMapper.delete(wrapper);
        System.out.println("影响记录数：" + i);
    }
}
