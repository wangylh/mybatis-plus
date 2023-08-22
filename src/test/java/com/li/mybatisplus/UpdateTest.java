package com.li.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.li.mybatisplus.dao.UserMapper;
import com.li.mybatisplus.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Author: lh
 * Date: 2023/8/21 15:57
 * Version: v1.0.0
 * Description: 新增
 **/
@SpringBootTest
public class UpdateTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void updateTest() {
        int i = userMapper.updateById(User.builder().name("赵乐际").id(1693542516752244737L).build());
        System.out.println("影响记录数：" + i);
    }

    @Test
    void updateTest1() {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name", "赵乐际");
        int update = userMapper.update(User.builder().name("王沪宁").build(), updateWrapper);
        System.out.println("影响记录数" + update);
    }

    @Test
    void updateTest2() {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>(User.builder().name("王沪宁").build());
        int update = userMapper.update(User.builder().name("蔡奇").build(), updateWrapper);
        System.out.println("影响记录数" + update);
    }

    @Test
    void updateTest3() {
        UpdateWrapper<User> updateWrapper = Wrappers.update();
        updateWrapper.set("name", "丁薛祥").eq("name", "蔡奇");
        int update = userMapper.update(null, updateWrapper);
        System.out.println("影响记录数" + update);
    }

    @Test
    void updateTest4() {
        LambdaUpdateWrapper<User> lambdaUpdateWrapper = Wrappers.lambdaUpdate();
        lambdaUpdateWrapper.set(User::getName, "韩正").eq(User::getName, "丁薛祥");

        int update = userMapper.update(null, lambdaUpdateWrapper);
        System.out.println("影响记录数" + update);
    }

    @Test
    void updateTest5() {
        boolean update = new LambdaUpdateChainWrapper<>(userMapper).set(User::getName, "胡锦涛").eq(User::getName, "韩正").update();
        System.out.println("影响记录数" + update);
    }
}
