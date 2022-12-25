package com.weiyuscode.wechatpush.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class TempMsgData {
    @JSONField(name="day")
    private TemplateMsgDataContent day;
    @JSONField(name="month")
    private TemplateMsgDataContent month;
    @JSONField(name="today_weekday")
    private TemplateMsgDataContent todayWeekday;
    @JSONField(name="temp_min")
    private TemplateMsgDataContent temperatureMin;
    @JSONField(name="temp_max")
    private TemplateMsgDataContent temperatureMax;
    @JSONField(name="temp_feel")
    private TemplateMsgDataContent temperatureFeel;
    @JSONField(name="weather_desc")
    private TemplateMsgDataContent weatherDetails;
    @JSONField(name="relation_len")
    private TemplateMsgDataContent relationLen;
    @JSONField(name="daily_msg")
    private TemplateMsgDataContent dailyMessage;
    @JSONField(name="daily_hint")
    private TemplateMsgDataContent dailyHint;
}
