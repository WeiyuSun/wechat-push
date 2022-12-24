package com.weiyuscode.wechatpush.controller;

import com.weiyuscode.wechatpush.pojo.AccessToken;
import com.weiyuscode.wechatpush.service.AccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController()
public class AccessTokenController {

    @Autowired
    private AccessTokenService accessTokenService;
    @Value("${wechat.config.accessTokenUrl}")
    private String accessTokenUri;
    @Autowired
    private RestTemplate restTemplate;

    @Scheduled(initialDelay = 1000, fixedRate = 3600000)
    public void renewAccessToken(){


        Map result = restTemplate.getForObject(accessTokenUri, Map.class);

        // TODO: add exception
        AccessToken accessToken = new AccessToken();
        accessToken.setAccessToken((String) result.get("access_token"));
        accessToken.setExpiresIn((Integer) result.get("expires_in"));
        System.out.println("new access token " + accessToken);
        accessTokenService.renewAccessToken(accessToken);
    }
}
