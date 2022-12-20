package com.weiyuscode.wechatpush.entity;

import lombok.Data;

@Data
public class Weather {
    Integer tempMin;
    Integer tempMax;
    Integer tempFeel;
    String weatherDetails;
}
