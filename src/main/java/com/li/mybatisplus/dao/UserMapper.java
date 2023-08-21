package com.li.mybatisplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.li.mybatisplus.entity.User;
import org.springframework.stereotype.Repository;

/**
 * Author: lh
 * Date: 2023/8/21 15:08
 * Version: v1.0.0
 * Description: 接口类
 **/
@Repository
public interface UserMapper extends BaseMapper<User> {
}
