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

@TableName("message")
@Accessors(chain = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "messageId", type = IdType.AUTO)
    private Long messageId;
    /**
     * 内容
     */
    private String content;
    /**
     * 发送时间
     */
    private LocalDateTime sendTime;
    /**
     * 收消息人ID
     */
    private Long receiverUserId;
    /**
     * 已读？
     */
    private boolean isRead;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 是否接收过 1 : 接收过  0 ：未接收
     */
    private Integer pending;
}
