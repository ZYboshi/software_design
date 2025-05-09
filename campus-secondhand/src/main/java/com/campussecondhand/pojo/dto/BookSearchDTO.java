package com.campussecondhand.pojo.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookSearchDTO {
    private String title;
    //1：降序，其他：升序
    private Integer sortOrder;
    private Integer pageNum;
    private Integer pageSize;
    private BigDecimal priceMin;
    private BigDecimal priceMax;
}
