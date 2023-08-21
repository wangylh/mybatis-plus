package com.li.mybatisplus;

import com.li.mybatisplus.dao.UserMapper;
import com.li.mybatisplus.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void select(){
        List<User> users = userMapper.selectList(null);
        Assert.notEmpty(users, String.valueOf(users.size()));
        users.forEach(System.out::println);
    }

}
