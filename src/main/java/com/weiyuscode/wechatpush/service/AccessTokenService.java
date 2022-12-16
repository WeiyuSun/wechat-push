package com.weiyuscode.wechatpush.service;

import com.weiyuscode.wechatpush.entity.AccessToken;

public interface AccessTokenService {
    public AccessToken getAccessToken();


    public void renewAccessToken(AccessToken accessToken);
}
