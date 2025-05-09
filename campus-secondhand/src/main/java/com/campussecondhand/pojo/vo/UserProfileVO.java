package com.campussecondhand.pojo.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserProfileVO {
    private String userName;
    /**
     * 身份证号
     */
    private String idNumber;
    /**
     * 性别
     */
    private String gender;
    /**
     * email
     */
    private String email;
    /**
     * 电话
     */
    private String phone;
}
