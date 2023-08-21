package com.li.mybatisplus;

import com.li.mybatisplus.dao.UserMapper;
import com.li.mybatisplus.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

/**
 * Author: lh
 * Date: 2023/8/21 15:57
 * Version: v1.0.0
 * Description: 新增
 **/
@SpringBootTest
public class InsertTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void insertTest(){
        User user = User.builder().name("习近平").age(60).managerId(1087982257332887553L).createTime(LocalDateTime.now()).build();
        userMapper.insert(user);
    }
}
