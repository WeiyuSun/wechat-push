package com.weiyuscode.wechatpush.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class TempMsgData {
    private Float temperatureMin;

    private Float temperatureMax;

    private Float temperatureFeel;

    private String weatherDetails;
//    public JSONObject toTemplateMsgJsonData(){
//        JSONObject data = new JSONObject();
//
//        JSONObject jsonDate = new JSONObject();
//        jsonDate.put("value", date);
//
//        JSONObject jsonWeekday = new JSONObject();
//        jsonWeekday.put("value", weekDay);
//
//        JSONObject jsonWeekdayHint = new JSONObject();
//        jsonWeekdayHint.put("value", weekDayHint);
//
////        JSONObject jsonTemperature = new JSONObject();
////        jsonTemperature.put("value", temperature);
//
//        JSONObject jsonRelationLen = new JSONObject();
//        jsonRelationLen.put("value", relationLen);
//
//        JSONObject jsonDailyMsg = new JSONObject();
//        jsonDailyMsg.put("value", dailyMsg);
//
//        data.put("date", jsonDate);
//        data.put("weekday",jsonWeekday);
//        data.put("weekday_hint",jsonWeekdayHint);
//        data.put("temperature",jsonTemperature);
//        data.put("relation_len",jsonRelationLen);
//        data.put("daily_msg",jsonDailyMsg);
//
//        return data;
//    }
}
