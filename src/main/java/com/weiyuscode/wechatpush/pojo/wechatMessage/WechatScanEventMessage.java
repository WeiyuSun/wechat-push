package com.weiyuscode.wechatpush.pojo.wechatMessage;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "xml")
public class WechatScanEventMessage extends WechatEventMessage{
}
