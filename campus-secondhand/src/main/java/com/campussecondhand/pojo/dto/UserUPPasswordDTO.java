package com.campussecondhand.pojo.dto;

import lombok.Data;

@Data
public class UserUPPasswordDTO {
    private String userName;
    private String oldPassword;
    private String newPassword;
}
