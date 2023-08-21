package com.li.mybatisplus.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Author: lh
 * Date: 2023/8/21 14:52
 * Version: v1.0.0
 * Description: 实体类
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@TableName("user")
public class User {

    //@TableId
    private Long id;
    //@TableField("name")
    private String name;
    private Integer age;
    private String email;
    private Long managerId;
    private LocalDateTime createTime;

}
