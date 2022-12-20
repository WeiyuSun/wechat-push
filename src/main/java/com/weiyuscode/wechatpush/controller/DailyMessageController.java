package com.weiyuscode.wechatpush.controller;

import com.alibaba.fastjson.JSONObject;
import com.weiyuscode.wechatpush.utils.Result;
import com.weiyuscode.wechatpush.service.DailyMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/dailymessage")
public class DailyMessageController {
    @Value("${dailyMsg.config.url}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DailyMessageService dailyMessageService;
    private boolean forbiddenAutoRenew;

    @GetMapping("/renewMessage")
    public Result<String> manualRenewMessage() {
        String result = getMessageFromUrl();
        dailyMessageService.renewMessage(result);
        forbiddenAutoRenew = true;
        return Result.OK(result);
    }

    @PostMapping("/renewHint")
    public Result manualHint(@RequestBody JSONObject data){
        dailyMessageService.renewHint((String) data.get("data"));
        return Result.OK();
    }

//    @Scheduled(cron = "0 30 7 * * ?", zone="America/Winnipeg")
    @Scheduled(initialDelay = 4000, fixedRate = 100000000)
    public void autoRenewMessage() {
        if (forbiddenAutoRenew) {
            forbiddenAutoRenew = false;
            System.out.println("拦截成功1");
            return;
        }
        dailyMessageService.renewMessage(getMessageFromUrl());
    }

    private String getMessageFromUrl() {
        JSONObject result = restTemplate.getForObject(url, JSONObject.class);
        return result.getJSONObject("result").getString("content");
    }
}
