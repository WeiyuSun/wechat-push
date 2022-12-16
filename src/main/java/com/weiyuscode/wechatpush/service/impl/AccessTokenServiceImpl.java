package com.weiyuscode.wechatpush.service.impl;
import com.weiyuscode.wechatpush.entity.AccessToken;
import com.weiyuscode.wechatpush.service.AccessTokenService;
import org.springframework.stereotype.Service;

@Service
public class AccessTokenServiceImpl implements AccessTokenService {
    private AccessToken accessToken;

    @Override
    public AccessToken getAccessToken() {
        return accessToken;
    }

    @Override
    public void renewAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
        System.out.println(this.accessToken);
    }
}
