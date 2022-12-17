package com.weiyuscode.wechatpush.entity;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class TemplateMsg {
    private String touser;
    private String templateID;
    public TempMsgData data;

    public JSONObject toTemplateMsgJson(){
        JSONObject body = new JSONObject();
        body.put("touser", touser);
        body.put("template_id", templateID);
        body.put("data",  data.toTemplateMsgJsonData());
        return body;
    }
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
