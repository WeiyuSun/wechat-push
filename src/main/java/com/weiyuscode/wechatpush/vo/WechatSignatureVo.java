package com.weiyuscode.wechatpush.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Data
public class WechatSignatureVo {
    @JSONField(name ="signature" )
    private String signature;
    @JSONField(name ="timestamp" )
    private String timestamp;
    @JSONField(name ="nonce" )
    private String nonce;
    @JSONField(name ="echostr" )
    private String echostr;
}
