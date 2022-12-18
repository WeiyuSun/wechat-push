package com.weiyuscode.wechatpush.entity;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class TemplateMessage {
    private String touser;
    private String templateID;
    public TempMsgData data;
}
