package com.weiyuscode.wechatpush.entity;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class TemplateMsg {
    @JSONField(name = "touser")
    private String touser;

    @JSONField(name = "template_id")
    private String templateID;

    @JSONField(name = "data")
    public TempMsgData data;
}



//{
//
//        "touser":"OPENID",
//        "template_id":"ngqIpbwh8bUfcSsECmogfXcV14J0tQlEpBO27izEYtY",
//
//
//        "data":{
//        "Date":" ",
//        "week-day":" ",
//        "week-day-hint": " ",
//        "temperature":" ",
//        "relation_len":" ",
//        "daily_msg":" "
//        }
//}
