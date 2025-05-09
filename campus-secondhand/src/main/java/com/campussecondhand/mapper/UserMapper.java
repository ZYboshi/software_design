package com.campussecondhand.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campussecondhand.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {


    @Select("select * from user where userName = #{userName}")
    User queryByUserName(String userName);
}
