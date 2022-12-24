package com.weiyuscode.wechatpush.service;

import com.weiyuscode.wechatpush.pojo.TemplateMessage;
import com.weiyuscode.wechatpush.vo.WechatMessageVo;

import java.util.Map;


public interface TempMessageService {
    TemplateMessage getTemplateMessage();

    void processMessageFromWechat(WechatMessageVo wechatMessageVo);
}
