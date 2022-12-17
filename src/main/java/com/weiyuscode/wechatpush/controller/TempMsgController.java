package com.weiyuscode.wechatpush.controller;

import com.alibaba.fastjson.JSONObject;
import com.weiyuscode.wechatpush.service.AccessTokenService;
import com.weiyuscode.wechatpush.service.TempMsgService;
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
    private TempMsgService tempMsgService;
    @Autowired
    private RestTemplate restTemplate;

    @Value("${wechat.config.templateMsgUrl}")
    private String templateMsgUrl;

    @Autowired
    private AccessTokenService accessTokenService;

    @Scheduled(initialDelay = 4000, fixedRate = 1000000)
    public void getTempMsg(){
        JSONObject body = tempMsgService.getTempMsg().toTemplateMsgJson();

        System.out.println("json message:");
        System.out.println(body);
        templateMsgUrl += accessTokenService.getAccessToken().getAccessToken();
        System.out.println(templateMsgUrl);
        String result = restTemplate.postForObject(templateMsgUrl, body, String.class);
        System.out.println(result);
    }
}
