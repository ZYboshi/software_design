package com.campussecondhand.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@TableName("systemNotice")
@Accessors(chain = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemNotice implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "noticeId", type = IdType.AUTO)
    private Long noticeId;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
}
