package com.weiyuscode.wechatpush.pojo;

import lombok.Data;

@Data
public class Weather {
    Integer tempMin;
    Integer tempMax;
    Integer tempFeel;
    String weatherDetails;
}
