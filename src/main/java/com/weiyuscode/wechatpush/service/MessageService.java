package com.weiyuscode.wechatpush.service;

import com.weiyuscode.wechatpush.pojo.TemplateMessage;
import com.weiyuscode.wechatpush.pojo.WechatMessage;


public interface MessageService {
    TemplateMessage getTemplateMessage();

    WechatMessage processMessageFromWechat(WechatMessage wechatMessage);
}
