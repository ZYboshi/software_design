package com.campussecondhand.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campussecondhand.mapper.UserMapper;
import com.campussecondhand.pojo.entity.User;
import com.campussecondhand.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
