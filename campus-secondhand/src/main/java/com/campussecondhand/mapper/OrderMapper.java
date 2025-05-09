package com.campussecondhand.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campussecondhand.pojo.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Orders> {

}
