package com.weiyuscode.wechatpush.controller;

import com.alibaba.fastjson.JSON;
import com.weiyuscode.wechatpush.pojo.TemplateMessage;
import com.weiyuscode.wechatpush.service.AccessTokenService;
import com.weiyuscode.wechatpush.service.DailyMessageService;
import com.weiyuscode.wechatpush.service.TempMessageService;
import com.weiyuscode.wechatpush.utils.Result;
import com.weiyuscode.wechatpush.vo.WechatMessageVo;
import com.weiyuscode.wechatpush.vo.WechatSignatureVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@RestController()
@RequestMapping("/message")
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

    @Scheduled(cron = "0 30 9 * * ?", zone = "America/Winnipeg")
//    @Scheduled(initialDelay = 7000, fixedRate = 10000000)
    public void getTempMsg() {
        TemplateMessage templateMessage = tempMessageService.getTemplateMessage();
        templateMsgUrl += accessTokenService.getAccessToken().getAccessToken();
        System.out.println(JSON.toJSONString(templateMessage));
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        String result = restTemplate.postForObject(templateMsgUrl, JSON.toJSONString(templateMessage), String.class);

        dailyMessageService.renewHint("");
        System.out.println(result);
    }

    @GetMapping(value = "/receiveMessage")
    public String checkSignature(@ModelAttribute WechatSignatureVo signature) {
        System.out.println(signature);
        return signature.getEchostr();
    }

    @PostMapping(value = "/receiveMessage", consumes = MediaType.ALL_VALUE,
            produces = MediaType.ALL_VALUE)
    public @ResponseBody Result<Object> receiveMessage(@RequestBody WechatMessageVo wechatMessageVo) {
        System.out.println(wechatMessageVo);
//        tempMessageService.processMessageFromWechat();
        return null;
    }
}
