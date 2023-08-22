package com.li.mybatisplus;

import com.li.mybatisplus.entity.User2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

/**
 * Author: lh
 * Date: 2023/8/21 15:57
 * Version: v1.0.0
 * Description: 新增
 **/
@SpringBootTest
public class ModelTest {

    @Test
    void modelTest() {
        User2 user2 = User2.builder().name("习近平").age(50).email("xjp@126.com").createTime(LocalDateTime.now()).managerId(1087982257332887553L).build();
        boolean insert = user2.insert();
        System.out.println(insert);
    }

    @Test
    void model1Test() {
        User2 user2 = new User2().selectById(1693837535853674497L);
        System.out.println(user2);
    }

    @Test
    void model2Test() {
        User2 user2 = User2.builder().id(1693837535853674497L).build().selectById();
        System.out.println(user2);
    }

    @Test
    void model3Test() {
        boolean b = User2.builder().id(1693837535853674497L).name("李强").build().updateById();
        System.out.println(b);

        boolean b1 = User2.builder().id(1693837535853674497L).name("李强").build().insertOrUpdate();
        System.out.println(b1);
    }

    @Test
    void model4Test() {
        boolean b = User2.builder().id(1693837535853674497L).build().deleteById();
        System.out.println(b);
    }

}
