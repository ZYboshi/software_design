package com.campussecondhand.pojo.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookSearchDetailDTO {
    private String title;
    private String imageFile;
    private String bookId;
    private BigDecimal price;
    private String description;
    private String userName;
}
