package com.weiyuscode.wechatpush.service;

import com.alibaba.fastjson.JSONObject;
import com.weiyuscode.wechatpush.entity.Weather;

public interface WeatherService {
    void renewWeather(JSONObject weatherInfo);

    Weather getWeather();
}
