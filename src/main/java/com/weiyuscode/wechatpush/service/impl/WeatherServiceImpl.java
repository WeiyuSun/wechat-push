package com.weiyuscode.wechatpush.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.weiyuscode.wechatpush.entity.Weather;
import com.weiyuscode.wechatpush.service.WeatherService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;

@Service
public class WeatherServiceImpl implements WeatherService {
    private Weather todayWeather;


    @Override
    public void renewWeather(JSONObject weatherInfo) {
        float kelvinsOffset = (float) 273.15;
        JSONObject main = weatherInfo.getJSONObject("main");
        float tempMin = main.getFloat("temp_min") - kelvinsOffset;
        float tempMax = main.getFloat("temp_max") - kelvinsOffset;
        float tempFeel = main.getFloat("feels_like") - kelvinsOffset;
        String weatherDetail = weatherInfo.getJSONArray("weather").getJSONObject(0).getString("description");

        Weather newWeather = new Weather();
        newWeather.setWeatherDetails(weatherDetail);
        newWeather.setTempFeel((int) tempFeel);
        newWeather.setTempMax((int) tempMax);
        newWeather.setTempMin((int) tempMin);
        todayWeather = newWeather;
        System.out.println("get weather info: " + todayWeather);
    }
    @Override
    public Weather getWeather() {
        return this.todayWeather;
    }
}
