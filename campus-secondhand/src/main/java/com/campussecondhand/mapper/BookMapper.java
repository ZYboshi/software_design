package com.campussecondhand.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campussecondhand.pojo.dto.BookDetailDTO;
import com.campussecondhand.pojo.dto.BookSearchDetailDTO;
import com.campussecondhand.pojo.entity.Book;
import com.campussecondhand.pojo.vo.BookVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

@Mapper
public interface BookMapper extends BaseMapper<Book> {

    IPage<BookDetailDTO> selectBookWithCategory(Page<Book> page, @Param("category") String category);

    IPage<BookSearchDetailDTO> selectBookWithPriceDESC(Page<Book> page,
                                                       @Param("title") String title,
                                                       @Param("priceMin") BigDecimal priceMin,
                                                       @Param("priceMax") BigDecimal priceMax);

    IPage<BookSearchDetailDTO> selectBookWithPriceASC(Page<Book> page,
                                                      @Param("title") String title,
                                                      @Param("priceMin") BigDecimal priceMin,
                                                      @Param("priceMax") BigDecimal priceMax);

    IPage<BookVO> listBookByStatus(Page<Book> page, @Param("status") String status);
}
