package com.campussecondhand.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long userId;
    private String userName;
    private String password;
    private String name;
    private String phone;
    private String email;
    private String idNumber;
}
