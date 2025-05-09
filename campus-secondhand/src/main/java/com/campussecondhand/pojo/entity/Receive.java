package com.campussecondhand.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@TableName("receive")
@Accessors(chain = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Receive implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户id
     */
    @TableId(value = "userId", type = IdType.INPUT)
    private Long userId;
    /**
     * 消息id
     */
    @TableId(value = "noticeId", type = IdType.INPUT)
    private Long noticeId;

}
