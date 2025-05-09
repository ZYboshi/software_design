package com.campussecondhand.pojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class BookPageQueryDTO implements Serializable {
    private String category;
    private Integer pageNum;
    private Integer pageSize;
}
