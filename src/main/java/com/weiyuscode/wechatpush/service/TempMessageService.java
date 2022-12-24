package com.weiyuscode.wechatpush.service;

import com.weiyuscode.wechatpush.pojo.TemplateMessage;
import com.weiyuscode.wechatpush.pojo.WechatMessage;


public interface TempMessageService {
    TemplateMessage getTemplateMessage();

    WechatMessage processMessageFromWechat(WechatMessage wechatMessage);
}
