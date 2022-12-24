package com.weiyuscode.wechatpush.pojo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "xml")
public class WechatMessage {
    @JacksonXmlProperty(localName = "ToUserName")
    private String toUserName;
    @JacksonXmlProperty(localName = "FromUserName")
    private String fromUserName;
    @JacksonXmlProperty(localName = "CreateTime")
    private Long createTime;
    @JacksonXmlProperty(localName = "MsgType")
    private String messageType;
    @JacksonXmlProperty(localName = "Content")
    private String content;
    @JacksonXmlProperty(localName = "MsgId")
    private Long msgId;
    @JacksonXmlProperty(localName = "MsgDataId")
    private String msgDataId;
    @JacksonXmlProperty(localName = "Idx")
    private Long idx;
    @JacksonXmlProperty(localName = "Format")
    private String format;
    @JacksonXmlProperty(localName = "MediaId")
    private String mediaId;
    @JacksonXmlProperty(localName = "Recognition")
    private String recognition;
    @JacksonXmlProperty(localName = "ThumbMediaId")
    private String thumbMediaId;
    @JacksonXmlProperty(localName = "Location_X")
    private Double locationX;
    @JacksonXmlProperty(localName = "Location_Y")
    private Double locationY;
    @JacksonXmlProperty(localName = "Scale")
    private String scale;
    @JacksonXmlProperty(localName = "Label")
    private String label;
    @JacksonXmlProperty(localName = "Title")
    private String title;
    @JacksonXmlProperty(localName = "Description")
    private String description;
    @JacksonXmlProperty(localName = "Url")
    private String url;
    @JacksonXmlProperty(localName = "Event")
    private String eventType;
    @JacksonXmlProperty(localName = "EventKey")
    private String eventKey;
    @JacksonXmlProperty(localName = "Precision")
    private Double precision;
    @JacksonXmlProperty(localName = "Longitude")
    private Double longitude;
    @JacksonXmlProperty(localName = "Latitude")
    private Double latitude;
    @JacksonXmlProperty(localName = "Ticket")
    private String ticket;
}
