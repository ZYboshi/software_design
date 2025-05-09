package com.campussecondhand.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@TableName("image")
@Accessors(chain = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "imageId", type = IdType.AUTO)
    private Long imageId;
    /**
     * 图片地址
     */
    private String imageFile;
    /**
     * 书籍Id
     */
    private Long bookId;
}
