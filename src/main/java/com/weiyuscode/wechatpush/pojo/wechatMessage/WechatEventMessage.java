package com.weiyuscode.wechatpush.pojo.wechatMessage;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "xml")
public class WechatEventMessage extends WechatMessage {
    @JacksonXmlProperty(localName = "Event")
    private String eventType;
}
