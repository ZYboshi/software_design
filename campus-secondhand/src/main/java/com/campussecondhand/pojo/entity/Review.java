package com.campussecondhand.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@TableName("review")
@Accessors(chain = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     *  书籍id
     */
    @TableId(value = "bookId", type = IdType.INPUT)
    private Long bookId;
    /**
     * 管理员id
     */
    @TableId(value = "adminId", type = IdType.INPUT)
    private Long adminId;
    /**
     * 行为
     */
    private String action;
    /**
     * 审查时间
     */
    private LocalDateTime reviewTime;
    /**
     * 原因
     */
    private String reason;
}
