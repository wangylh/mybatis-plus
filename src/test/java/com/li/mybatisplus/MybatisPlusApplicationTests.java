package com.li.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.li.mybatisplus.dao.UserMapper;
import com.li.mybatisplus.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MybatisPlusApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void selectTest(){
        List<User> users = userMapper.selectList(null);
        Assert.notEmpty(users, String.valueOf(users.size()));
        users.forEach(System.out::println);
    }

    @Test
    void selectTest1(){
        User user = userMapper.selectById(1693535919040638978L);
        System.out.println(user);

        List<User> users = userMapper.selectBatchIds(Arrays.asList(1693535919040638978L, 1693542516752244737L));
        users.forEach(System.out::println);

        Map<String, Object> map = new HashMap<>(){
            {
                put("name", "习近平");
            }
        };
        List<User> users1 = userMapper.selectByMap(map);
        System.out.println(users1);
    }

    @Test
    void selectWrapperTest(){
        //QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //QueryWrapper<User> queryWrapper = Wrappers.<User>query();
        QueryWrapper<User> queryWrapper = Wrappers.query();
        queryWrapper.like("name", "雨").lt("age", 40);
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);

        queryWrapper.clear();
        queryWrapper.like("name", "雨").between("age", 20, 40).isNotNull("email");
        List<User> users1 = userMapper.selectList(queryWrapper);
        users1.forEach(System.out::println);

        queryWrapper.clear();
        queryWrapper.likeRight("name", "王").or().ge("age", 25).orderByDesc("age").orderByAsc("id");
        List<User> users2 = userMapper.selectList(queryWrapper);
        users2.forEach(System.out::println);
    }

}
