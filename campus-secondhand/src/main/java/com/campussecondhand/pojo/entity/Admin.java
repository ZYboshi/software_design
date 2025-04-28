package com.campussecondhand.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@TableName("admin")
@Accessors(chain = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "adminId", type = IdType.AUTO)
    private Long adminId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * email
     */
    private String email;
    /**
     * 电话
     */
    private String phone;
    /**
     * 等级
     */
    private String permissionLevel;
    /**
     * 上次登录时间
     */
    private LocalDateTime lastLoginTime;
}
