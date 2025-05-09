package com.campussecondhand.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.campussecondhand.pojo.entity.ResultMessage;

public class MessageUtils {

    public static String getMessage(boolean isSystemNotice, String fromName, Object content) {
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setSystemNotice(isSystemNotice);
        resultMessage.setContent(content);
        if(StrUtil.isNotBlank(fromName)) {
            resultMessage.setFromName(fromName);
        }
        return JSONUtil.toJsonStr(resultMessage);
    }
}
