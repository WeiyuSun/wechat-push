package com.weiyuscode.wechatpush.pojo;
import com.weiyuscode.wechatpush.utils.TextColorUtils;
import lombok.Data;

@Data
public class TemplateMsgDataContent {
    private String value;
    private String color;

    public TemplateMsgDataContent(String value){
        this.value = value;
        color = TextColorUtils.black; // default, black
    }

    public TemplateMsgDataContent(String value, String color){
        this.value = value;
        this.color = color;
    }
}
