package com.weiyuscode.wechatpush.service;

import com.weiyuscode.wechatpush.pojo.TemplateMessage;
import com.weiyuscode.wechatpush.pojo.WechatMessage;

import java.util.List;


public interface MessageService {
    List<TemplateMessage> getTemplateMessages();

    WechatMessage processMessageFromWechat(WechatMessage wechatMessage);
}
