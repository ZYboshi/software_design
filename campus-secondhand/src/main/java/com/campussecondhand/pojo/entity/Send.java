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

@TableName("send")
@Accessors(chain = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Send implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 管理员id
     */
    @TableId(value = "adminId", type = IdType.INPUT)
    private Long adminId;
    /**
     * 消息id
     */
    @TableId(value = "noticeId", type = IdType.INPUT)
    private Long noticeId;
    /**
     * 发送时间
     */
    private LocalDateTime sendTime;
}
