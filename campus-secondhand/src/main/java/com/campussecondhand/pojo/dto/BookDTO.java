package com.campussecondhand.pojo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class BookDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 书名
     */
    private String title;
    /**
     * 作者
     */
    private String author;
    /**
     * 出版社
     */
    private String publisher;
    /**
     * 描述
     */
    private String description;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 种类
     */
    private String category;
    /**
     * isbn
     */
    private String isbn;
    /**
     * 用户id
     */
    private Long userId;
    private String userName;
    private List<String> imageFile;
}
