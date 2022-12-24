package com.weiyuscode.wechatpush.pojo.wechatMessage;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@JacksonXmlRootElement(localName = "xml")
@Data
public class WechatSubscribeEventMessage extends WechatEventMessage{
}
