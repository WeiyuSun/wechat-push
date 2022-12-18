package com.weiyuscode.wechatpush.entity;

import lombok.Data;

@Data
public class Weather {
    Float tempMin;
    Float tempMax;
    Float tempFeel;
    String weatherDetails;
}
