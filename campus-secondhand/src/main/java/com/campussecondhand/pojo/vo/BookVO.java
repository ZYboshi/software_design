package com.campussecondhand.pojo.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class BookVO {
    private String bookId;
    private String title;
    private List<String> imageFile;
    private BigDecimal price;
    private String description;
    private String userName;
    private String userId;
}
