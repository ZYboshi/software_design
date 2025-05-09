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

@TableName("logistics")
@Accessors(chain = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Logistics implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "logisticsId", type = IdType.AUTO)
    private Long logisticsId;
    /**
     * 收货地址
     */
    private String recipientAddress;
    /**
     * 收货名
     */
    private String recipientName;
    /**
     * 收货电话号
     */
    private String recipientPhone;
    /**
     * 号码
     */
    private String trackingNumber;
    /**
     * 发货时间
     */
    private LocalDateTime deliveryTime;
    /**
     * 物流公司
     */
    private String logisticsCompany;
    /**
     * 订单Id
     */
    private Long orderId;
}
