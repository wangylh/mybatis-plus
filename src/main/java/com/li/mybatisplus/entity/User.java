package com.li.mybatisplus.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Author: lh
 * Date: 2023/8/21 14:52
 * Version: v1.0.0
 * Description: 实体类
 **/
@Data
public class User {

    private Long id;
    private String name;
    private Integer age;
    private String email;
    private Long managerId;
    private LocalDateTime createTime;

}
