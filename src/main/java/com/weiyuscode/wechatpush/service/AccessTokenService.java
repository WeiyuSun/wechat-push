package com.weiyuscode.wechatpush.service;

import com.weiyuscode.wechatpush.pojo.AccessToken;

public interface AccessTokenService {
    AccessToken getAccessToken();
    void renewAccessToken(AccessToken accessToken);
}
