package com.weiyuscode.wechatpush.service.impl;

import com.weiyuscode.wechatpush.entity.TempMsgData;
import com.weiyuscode.wechatpush.entity.TemplateMessage;
import com.weiyuscode.wechatpush.entity.TemplateMsgDataContent;
import com.weiyuscode.wechatpush.entity.Weather;
import com.weiyuscode.wechatpush.service.DailyMessageService;
import com.weiyuscode.wechatpush.service.TempMessageService;
import com.weiyuscode.wechatpush.service.WeatherService;
import com.weiyuscode.wechatpush.utils.DateUtils;
import com.weiyuscode.wechatpush.utils.TextColorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TempMessageServiceImpl implements TempMessageService {

    @Value("${wechat.config.openID}")
    private String touser;
    @Value("${wechat.config.tempID}")
    private String tempID;

    @Value("${wechat.config.relationStartDate}")
    private String relationStartDate;

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private DailyMessageService dailyMessageService;

    /**
     * get the json template message
     *
     */
    @Override
    public TemplateMessage getTemplateMessage() {
        Weather weather = weatherService.getWeather();
        TemplateMessage msg = new TemplateMessage();
        TempMsgData tempMsgData = new TempMsgData();

        tempMsgData.setTemperatureMax(generateTemperatureContent(weather.getTempMax(), true));
        tempMsgData.setTemperatureMin(generateTemperatureContent(weather.getTempMin(), true));
        tempMsgData.setTemperatureFeel(generateFeelTemperatureContent(weather, true));

        tempMsgData.setWeatherDetails(new TemplateMsgDataContent(weather.getWeatherDetails()));

        SimpleDateFormat dateFormat = DateUtils.getYmdDateFormat();
        Date start = null;
        try {
            start = dateFormat.parse(relationStartDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Long relationLen = DateUtils.daysBetween(start, new Date());
        tempMsgData.setRelationLen(new TemplateMsgDataContent(relationLen.toString(), TextColorUtils.orange));
        tempMsgData.setDailyMessage(new TemplateMsgDataContent(dailyMessageService.getTodayMessage(), TextColorUtils.pink));
        tempMsgData.setDailyHint(new TemplateMsgDataContent(dailyMessageService.getTodayHint(), TextColorUtils.pink));
        msg.setTouser(touser);
        msg.setTemplateID(tempID);
        msg.setData(tempMsgData);
        return msg;
    }

    private TemplateMsgDataContent generateTemperatureContent(Integer temperature, boolean addEmoji){
        String content = "" + temperature + "°C";
        String textColor = TextColorUtils.black;
        return getTemplateMsgDataContent(addEmoji, content, temperature);
    }

    private TemplateMsgDataContent generateFeelTemperatureContent(Weather weatherInfo, boolean addEmoji){
        String content =  weatherInfo.getTempFeel() + "°C";
        String textColor = TextColorUtils.black;

        Integer tempFeel = weatherInfo.getTempFeel();
        Integer tempMax = weatherInfo.getTempMax();
        Integer tempMin = weatherInfo.getTempMin();

        if (tempFeel - tempMax >= 5 || tempMin - tempFeel >= 5) {
            content += "别被骗到>_<!";
        }

        return getTemplateMsgDataContent(addEmoji, content, tempFeel);
    }

    private TemplateMsgDataContent getTemplateMsgDataContent(boolean addEmoji, String content, Integer tempFeel) {
        String textColor;
        if(tempFeel > 25){ // > 25
            textColor = TextColorUtils.hotTemperatureTextColor;
            content = (addEmoji) ? content + "\uD83E\uDD75" : content;
        } else if(tempFeel > 0) { // <= 25 && > 0
            textColor = TextColorUtils.normalTemperatureTextColor;
            content = (addEmoji) ? content + "\uD83E\uDD17" : content;
        } else { // <= 0
            textColor = TextColorUtils.coldTemperatureTextColor;
            content = (addEmoji) ? content + "\uD83E\uDD76" : content;
        }
        return new TemplateMsgDataContent(content, textColor);
    }
}
