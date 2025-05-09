package com.campussecondhand.pojo.entity;

import lombok.Data;

@Data
public class ChatMessage {
    private boolean isSystemNotice;
    private Long toUserId;
    private String content;
}
