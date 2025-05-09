package com.campussecondhand.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campussecondhand.pojo.entity.Image;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ImageMapper extends BaseMapper<Image> {
    void addBatch(List<Image> list);

    @Select("select imageFile from image where bookId = #{bookId}")
    List<String> selectByBookId(@Param("bookId") String bookId);
}
