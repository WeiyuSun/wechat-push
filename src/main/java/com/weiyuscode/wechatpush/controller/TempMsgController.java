package com.weiyuscode.wechatpush.controller;

import com.alibaba.fastjson.JSON;
import com.weiyuscode.wechatpush.entity.TemplateMessage;
import com.weiyuscode.wechatpush.service.AccessTokenService;
import com.weiyuscode.wechatpush.service.DailyMessageService;
import com.weiyuscode.wechatpush.service.TempMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
@RestController()
public class TempMsgController {
    @Autowired
    private TempMessageService tempMessageService;

    @Autowired
    private DailyMessageService dailyMessageService;
    @Autowired
    private RestTemplate restTemplate;

    @Value("${wechat.config.templateMsgUrl}")
    private String templateMsgUrl;

    @Autowired
    private AccessTokenService accessTokenService;

    @Scheduled(cron = "0 25 17 * * ?", zone="America/Winnipeg")
    public void getTempMsg(){
        TemplateMessage templateMessage = tempMessageService.getTemplateMessage();
        templateMsgUrl += accessTokenService.getAccessToken().getAccessToken();
        System.out.println(JSON.toJSONString(templateMessage));
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        String result = restTemplate.postForObject(templateMsgUrl, JSON.toJSONString(templateMessage), String.class);

        dailyMessageService.renewHint("");
        System.out.println(result);
    }
}
