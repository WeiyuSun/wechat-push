package com.weiyuscode.wechatpush.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class TempMsgData {
    private String date;
    private String weekDay;
    private String weekDayHint;
    private String temperature;
    private String relationLen;
    private String dailyMsg;

    public JSONObject toTemplateMsgJsonData(){
        JSONObject data = new JSONObject();

        JSONObject jsonDate = new JSONObject();
        jsonDate.put("value", date);

        JSONObject jsonWeekday = new JSONObject();
        jsonWeekday.put("value", weekDay);

        JSONObject jsonWeekdayHint = new JSONObject();
        jsonWeekdayHint.put("value", weekDayHint);

        JSONObject jsonTemperature = new JSONObject();
        jsonTemperature.put("value", temperature);

        JSONObject jsonRelationLen = new JSONObject();
        jsonRelationLen.put("value", relationLen);

        JSONObject jsonDailyMsg = new JSONObject();
        jsonDailyMsg.put("value", dailyMsg);

        data.put("date", jsonDate);
        data.put("weekday",jsonWeekday);
        data.put("weekday_hint",jsonWeekdayHint);
        data.put("temperature",jsonTemperature);
        data.put("relation_len",jsonRelationLen);
        data.put("daily_msg",jsonDailyMsg);

        return data;
    }
}
