package com.li.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Author: lh
 * Date: 2023/8/21 14:52
 * Version: v1.0.0
 * Description: 实体类
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("user")
public class User2 extends Model<User2> {

    //@TableId
    private Long id;
    //@TableField("name")
    //@TableField(condition = SqlCondition.LIKE)
    private String name;
    private Integer age;
    private String email;
    private Long managerId;
    private LocalDateTime createTime;

    //private transient String remark;
    //private static String remark;
    @TableField(exist = false)
    private String remark;
}
