package com.weiyuscode.wechatpush.controller;

import com.alibaba.fastjson.JSON;
import com.weiyuscode.wechatpush.pojo.TemplateMessage;
import com.weiyuscode.wechatpush.service.AccessTokenService;
import com.weiyuscode.wechatpush.service.DailyMessageService;
import com.weiyuscode.wechatpush.service.MessageService;
import com.weiyuscode.wechatpush.pojo.WechatMessage;
import com.weiyuscode.wechatpush.pojo.WechatSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@RestController()
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private DailyMessageService dailyMessageService;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${wechat.config.templateMsgUrl}")
    private String templateMsgUrl;
    @Autowired
    private DailyMessageController dailyMessageController;
    @Autowired
    private WeatherController weatherController;
    @Autowired
    private AccessTokenService accessTokenService;
    //@Scheduled(cron = "0 30 9 * * ?", zone = "America/Winnipeg")
    @Scheduled(initialDelay = 7000, fixedRate = 10000)
    public void sendTempMsg() {
        dailyMessageController.autoRenewMessage();
        weatherController.getWeather();

        TemplateMessage templateMessage = messageService.getTemplateMessage();
        String templateUrlWithAccessToken = templateMsgUrl + accessTokenService.getAccessToken().getAccessToken();
        System.out.println(JSON.toJSONString(templateMessage));
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        String result = restTemplate.postForObject(templateUrlWithAccessToken, JSON.toJSONString(templateMessage), String.class);

        dailyMessageService.renewHint("");
        System.out.println(result);
    }

    @GetMapping(value = "/receiveMessage")
    public String checkSignature(@ModelAttribute WechatSignature signature) {
        System.out.println(signature);
        return signature.getEchostr();
    }

    @PostMapping(value = "/receiveMessage", consumes = MediaType.ALL_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE)
    public @ResponseBody WechatMessage receiveMessage(@RequestBody WechatMessage wechatMessageVo) {
        WechatMessage wechatMessage = messageService.processMessageFromWechat(wechatMessageVo);
        return wechatMessage;
    }
}
