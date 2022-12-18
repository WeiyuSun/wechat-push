package com.weiyuscode.wechatpush.controller;

import com.alibaba.fastjson.JSONObject;
import com.weiyuscode.wechatpush.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WeatherController {
    @Value("${openWeather.config.url}")
    private String url;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WeatherService weatherService;


    @Scheduled(initialDelay = 4000, fixedRate = 20000000)
    public void getWeather(){
        JSONObject result = restTemplate.getForObject(url, JSONObject.class);
        System.out.println(result);
        weatherService.renewWeather(result);
    }
}
