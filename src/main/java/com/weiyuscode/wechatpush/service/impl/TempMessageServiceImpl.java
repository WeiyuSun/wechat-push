package com.weiyuscode.wechatpush.service.impl;
import com.alibaba.fastjson.JSONObject;
import com.weiyuscode.wechatpush.entity.TempMsgData;
import com.weiyuscode.wechatpush.entity.TemplateMessage;
import com.weiyuscode.wechatpush.entity.Weather;
import com.weiyuscode.wechatpush.service.TempMessageService;
import com.weiyuscode.wechatpush.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TempMessageServiceImpl implements TempMessageService {

    @Value("${wechat.config.openID}")
    private String touser;
    @Value("${wechat.config.tempID}")
    private String tempID;
    @Autowired
    private WeatherService weatherService;

    /**
     * get the json template message
     * @return
     */
    @Override
    public JSONObject getJsonTempMsg() {
        Weather weather = weatherService.getWeather();
        TemplateMessage msg = new TemplateMessage();
        TempMsgData tempMsgData = new TempMsgData();

        tempMsgData.setTemperatureMax(weather.getTempMax());
        tempMsgData.setTemperatureMin(weather.getTempMin());
        tempMsgData.setTemperatureFeel(weather.getTempFeel());
        tempMsgData.setWeatherDetails(weather.getWeatherDetails());

        msg.setTouser(touser);
        msg.setTemplateID(tempID);
        msg.setData(tempMsgData);
        return toJsonTempMessage(msg);
    }
    private JSONObject toJsonTempMessage(TemplateMessage templateMessage){
        JSONObject jsonTempMessage = new JSONObject();
        JSONObject jsonData = new JSONObject();

        TempMsgData data = templateMessage.getData();
        System.out.println("from tempMsgService: " + data);

        JSONObject jsonTempMin = new JSONObject();
        jsonTempMin.put("value", data.getTemperatureMin());
        JSONObject jsonTempMax = new JSONObject();
        jsonTempMax.put("value", data.getTemperatureMax());
        JSONObject jsonTempFeel = new JSONObject();
        jsonTempFeel.put("value", data.getTemperatureFeel());
        JSONObject jsonWeatherDetails= new JSONObject();
        jsonWeatherDetails.put("value", data.getWeatherDetails());

        jsonData.put("temp_min", jsonTempMin);
        jsonData.put("temp_max", jsonTempMax);
        jsonData.put("temp_feel", jsonTempFeel);
        jsonData.put("weather_desc", jsonWeatherDetails);

        jsonTempMessage.put("data", jsonData);
        jsonTempMessage.put("touser", templateMessage.getTouser());
        jsonTempMessage.put("template_id", templateMessage.getTemplateID());

        return jsonTempMessage;
    }
}
