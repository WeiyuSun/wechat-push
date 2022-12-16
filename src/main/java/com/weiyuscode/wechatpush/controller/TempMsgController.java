package com.weiyuscode.wechatpush.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.weiyuscode.wechatpush.entity.TempMsgData;
import com.weiyuscode.wechatpush.entity.TemplateMsg;
import com.weiyuscode.wechatpush.service.AccessTokenService;
import com.weiyuscode.wechatpush.service.TempMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
//
//@JSONField(name = "date")
//public String date;
//
//@JSONField(name = "weekday")
//public String weekDay;
//
//@JSONField(name = "weekday_hint")
//public String weekDayHint;
//
//@JSONField(name = "temperature")
//public String temperature;
//
//@JSONField(name = "relation_len")
//public String relationLen;
//
//@JSONField(name = "daily_msg")
//public String dailyMsg;

@RestController()
public class TempMsgController {
    @Autowired
    private TempMsgService tempMsgService;
    @Autowired
    private RestTemplate restTemplate;

    @Value("${wechat.config.templateMsgUrl}")
    private String templateMsgUrl;

    @Autowired
    private AccessTokenService accessTokenService;

    @Scheduled(initialDelay = 4000, fixedRate = 1000000)
    public void getTempMsg(){
        TemplateMsg templateMsg = tempMsgService.getTempMsg();
        TempMsgData tempMsgData = templateMsg.getData();
        JSONObject body = new JSONObject(new LinkedHashMap<>());
        Map<String, Object> data = new HashMap<>();

        data.put("date", tempMsgData.getDate());
        data.put("weekday", tempMsgData.getWeekDay());
        data.put("weekday_hint", tempMsgData.getWeekDayHint());
        data.put("temperature", tempMsgData.getTemperature());
        data.put("relation_len", tempMsgData.getRelationLen());
        data.put("daily_msg", tempMsgData.getDailyMsg());
        body.put("data", new JSONObject(data));

        body.put("touser", templateMsg.getTouser());
        body.put("template_id", templateMsg.getTemplateID());
        //String jsonResult = JSON.toJSONString(body);
//        System.out.println(templateMsg);
       // System.out.println(jsonResult);
        templateMsgUrl += accessTokenService.getAccessToken().getAccessToken();
        System.out.println(templateMsgUrl);
        String result = restTemplate.postForObject(templateMsgUrl, body, String.class);
        System.out.println(result);
    }
}
