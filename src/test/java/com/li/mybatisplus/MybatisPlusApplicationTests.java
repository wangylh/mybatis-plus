package com.li.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
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
    void selectTest() {
        List<User> users = userMapper.selectList(null);
        Assert.notEmpty(users, String.valueOf(users.size()));
        users.forEach(System.out::println);
    }

    @Test
    void selectTest1() {
        User user = userMapper.selectById(1693535919040638978L);
        System.out.println(user);

        List<User> users = userMapper.selectBatchIds(Arrays.asList(1693535919040638978L, 1693542516752244737L));
        users.forEach(System.out::println);

        Map<String, Object> map = new HashMap<>() {
            {
                put("name", "习近平");
            }
        };
        List<User> users1 = userMapper.selectByMap(map);
        System.out.println(users1);
    }

    @Test
    void selectWrapperTest() {
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

    @Test
    void selectWrapper1Test() {
        QueryWrapper<User> query = Wrappers.query();
        query.apply("date_format(create_time,'%Y-%m-%d')={0}", "2019-01-14").inSql("manager_id", "select id from user where name like '王%'");
        List<User> users = userMapper.selectList(query);
        users.forEach(System.out::println);
    }

    @Test
    void selectWrapper2Test() {
        QueryWrapper<User> query = Wrappers.query();
        query.likeRight("name", "王").and(wq -> wq.lt("age", 40).or().isNotNull("email"));
        List<User> users = userMapper.selectList(query);
        users.forEach(System.out::println);

        query.clear();
        query.likeRight("name", "王").or(wq -> wq.lt("age", 40).gt("age", 20).isNotNull("email"));
        List<User> users1 = userMapper.selectList(query);
        users1.forEach(System.out::println);

        query.clear();
        query.nested(wq -> wq.lt("age", 40).or().isNotNull("email")).likeRight("name", "王");
        List<User> users2 = userMapper.selectList(query);
        users2.forEach(System.out::println);

        query.clear();
        query.in("age", Arrays.asList(31,32,33,34));
        List<User> users3 = userMapper.selectList(query);
        users3.forEach(System.out::println);

        query.clear();
        query.in("age", Arrays.asList(31,32,33,34)).last("limit 1");
        List<User> users4 = userMapper.selectList(query);
        users4.forEach(System.out::println);
    }

    @Test
    void selectWrapper3Test() {
        QueryWrapper<User> queryWrapper = Wrappers.query();
        queryWrapper.select("id","name").like("name", "雨").lt("age", 40);
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);

        queryWrapper.clear();
        queryWrapper.select(User.class, info -> !info.getColumn().equals("create_time") && !info.getColumn().equals("email")).like("name", "雨").lt("age", 40);
        List<User> users1 = userMapper.selectList(queryWrapper);
        users1.forEach(System.out::println);
    }

    @Test
    void selectWrapper4Test() {
        String name = "";
        String email = "l";
        QueryWrapper<User> queryWrapper = Wrappers.query();
       queryWrapper.like(StringUtils.isNotEmpty(name), "name", name).like(StringUtils.isNotEmpty("email"), "email", email);
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    void selectWrapper5Test() {
        QueryWrapper<User> queryWrapper = Wrappers.query(User.builder().name("习近平").build());
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    void selectWrapper6Test() {
        QueryWrapper<User> queryWrapper = Wrappers.query();
        Map<String, Object> map = new HashMap<>();
        map.put("name", "王天风");
        map.put("age", null);
        //queryWrapper.allEq(map);
        //queryWrapper.allEq(map, false);
        queryWrapper.allEq((k,v) -> !k.equals("name"),map);
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    void selectWrapper7Test() {
        QueryWrapper<User> queryWrapper = Wrappers.query();
        queryWrapper.select("id", "name");
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);

        queryWrapper.clear();
        queryWrapper.select("avg(age)", "min(age) minage", "max(age) maxage").groupBy("manager_id").having("sum(age)<{0}", 500);
        List<Map<String, Object>> maps1 = userMapper.selectMaps(queryWrapper);
        maps1.forEach(System.out::println);

        queryWrapper.clear();
        List<Object> objects = userMapper.selectObjs(queryWrapper);
        objects.forEach(System.out::println);

        Long aLong = userMapper.selectCount(queryWrapper);
        System.out.println(aLong);

        queryWrapper.allEq(new HashMap<String, String>(){{put("name","习近平");}});
        User user = userMapper.selectOne(queryWrapper);
        System.out.println(user);
    }

    @Test
    void selectWrapper8Test() {
        //LambdaQueryWrapper<User> lambda = new QueryWrapper<User>().lambda();
        //LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.likeRight(User::getName, "王").and(lqw -> lqw.lt(User::getAge, 40).or().isNotNull(User::getEmail));
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    void selectWrapper9Test() {
        List<User> list = new LambdaQueryChainWrapper<>(userMapper).eq(User::getAge, 40).list();
        list.forEach(System.out::println);
    }
}
