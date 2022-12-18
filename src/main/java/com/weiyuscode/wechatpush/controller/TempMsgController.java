package com.weiyuscode.wechatpush.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.hsf.HSFJSONUtils;
import com.weiyuscode.wechatpush.entity.TemplateMessage;
import com.weiyuscode.wechatpush.service.AccessTokenService;
import com.weiyuscode.wechatpush.service.TempMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
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
    private TempMessageService tempMessageService;
    @Autowired
    private RestTemplate restTemplate;

    @Value("${wechat.config.templateMsgUrl}")
    private String templateMsgUrl;

    @Autowired
    private AccessTokenService accessTokenService;

    @Scheduled(initialDelay = 9000, fixedRate = 1000000)
    public void getTempMsg(){
        //JSONObject body = tempMsgService.getTempMsg().toTemplateMsgJson();
        JSONObject templateMessage = tempMessageService.getJsonTempMsg();
        templateMsgUrl += accessTokenService.getAccessToken().getAccessToken();
        System.out.println(templateMessage);
        String result = restTemplate.postForObject(templateMsgUrl, templateMessage, String.class);
        System.out.println(result);
    }
}
