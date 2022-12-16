package com.weiyuscode.wechatpush.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class TempMsgData {
        @JSONField(name = "date")
        public String date;

        @JSONField(name = "weekday")
        public String weekDay;

        @JSONField(name = "weekday_hint")
        public String weekDayHint;

        @JSONField(name = "temperature")
        public String temperature;

        @JSONField(name = "relation_len")
        public String relationLen;

        @JSONField(name = "daily_msg")
        public String dailyMsg;
}
