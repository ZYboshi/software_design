package com.campussecondhand.pojo.entity;

import lombok.Data;

@Data
public class ResultMessage {
    private boolean isSystemNotice;
    private String fromName;
    private Object content;
}
