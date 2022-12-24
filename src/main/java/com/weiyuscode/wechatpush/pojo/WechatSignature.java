package com.weiyuscode.wechatpush.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class WechatSignature {
    @JSONField(name ="signature" )
    private String signature;
    @JSONField(name ="timestamp" )
    private String timestamp;
    @JSONField(name ="nonce" )
    private String nonce;
    @JSONField(name ="echostr" )
    private String echostr;
}
