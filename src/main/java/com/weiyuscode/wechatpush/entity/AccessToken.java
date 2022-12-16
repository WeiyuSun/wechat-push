package com.weiyuscode.wechatpush.entity;

import lombok.Data;

@Data
public class AccessToken {
    private String accessToken;
    private Integer expiresIn;
}
