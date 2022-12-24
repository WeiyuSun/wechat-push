package com.weiyuscode.wechatpush.pojo;

import lombok.Data;

@Data
public class AccessToken {
    private String accessToken;
    private Integer expiresIn;
}
