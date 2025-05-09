package com.campussecondhand.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.cglib.core.Local;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@TableName("orders")
@Accessors(chain = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "orderId", type = IdType.AUTO)
    private Long orderId;
    /**
     * 支付方式
     */
    private String paymentMethod;
    /**
     * 状态
     */
    private Long status;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 卖方ID
     */
    private Long sellerId;
    /**
     * 支付时间
     */
    private LocalDateTime paymentTime;
    /**
     * 总金额
     */
    private BigDecimal totalAmount;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 书籍id
     */
    private Long bookId;
}
