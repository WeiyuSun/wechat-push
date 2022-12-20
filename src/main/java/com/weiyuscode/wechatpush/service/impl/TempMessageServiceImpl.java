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

import static com.weiyuscode.wechatpush.utils.TemperatureUtils.generateFeelTemperatureContent;
import static com.weiyuscode.wechatpush.utils.TemperatureUtils.generateTemperatureContent;

@Service
public class TempMessageServiceImpl implements TempMessageService {

    @Value("${wechat.config.openID.me}")
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
}
