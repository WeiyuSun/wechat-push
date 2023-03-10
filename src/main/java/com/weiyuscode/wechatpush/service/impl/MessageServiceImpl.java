package com.weiyuscode.wechatpush.service.impl;

import com.weiyuscode.wechatpush.pojo.TempMsgData;
import com.weiyuscode.wechatpush.pojo.TemplateMessage;
import com.weiyuscode.wechatpush.pojo.TemplateMsgDataContent;
import com.weiyuscode.wechatpush.pojo.Weather;
import com.weiyuscode.wechatpush.service.DailyMessageService;
import com.weiyuscode.wechatpush.service.MessageService;
import com.weiyuscode.wechatpush.service.WeatherService;
import com.weiyuscode.wechatpush.utils.DateUtils;
import com.weiyuscode.wechatpush.utils.TextColorUtils;
import com.weiyuscode.wechatpush.utils.WechatEventType;
import com.weiyuscode.wechatpush.utils.WechatMessageType;
import com.weiyuscode.wechatpush.pojo.WechatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.unit.DataUnit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.weiyuscode.wechatpush.utils.TemperatureUtils.generateFeelTemperatureContent;
import static com.weiyuscode.wechatpush.utils.TemperatureUtils.generateTemperatureContent;

@Service
public class MessageServiceImpl implements MessageService {

    @Value("${wechat.config.openID.me}")
    private String me;
    @Value("${wechat.config.openID.special}")
    private String myGirl;
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
     */
    @Override
    public List<TemplateMessage> getTemplateMessages() {
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH) + 1;
        System.out.println("month is " + month);
        int weekday = calendar.get(Calendar.DAY_OF_WEEK);

        Weather weather = weatherService.getWeather();
        TemplateMessage messageToMe = new TemplateMessage();
        TemplateMessage messageToMyGirl = new TemplateMessage();
        TempMsgData tempMsgData = new TempMsgData();

        tempMsgData.setDay(new TemplateMsgDataContent(String.valueOf(day), TextColorUtils.green));
        tempMsgData.setMonth(new TemplateMsgDataContent(String.valueOf(month), TextColorUtils.green));
        tempMsgData.setTodayWeekday(new TemplateMsgDataContent(DateUtils.getWeekdayContent(weekday)));

        tempMsgData.setTemperatureMax(generateTemperatureContent(weather.getTempMax(), false));
        tempMsgData.setTemperatureMin(generateTemperatureContent(weather.getTempMin(), false));
        tempMsgData.setTemperatureFeel(generateFeelTemperatureContent(weather, false));

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

        List<TemplateMessage> data = new ArrayList<>();

        messageToMe.setTouser(me);
        messageToMe.setTemplateID(tempID);
        messageToMe.setData(tempMsgData);

        messageToMyGirl.setTouser(myGirl);
        messageToMyGirl.setTemplateID(tempID);
        messageToMyGirl.setData(tempMsgData);

        data.add(messageToMe);
        data.add(messageToMyGirl);
        return data;
    }

    @Override
    public WechatMessage processMessageFromWechat(WechatMessage wechatMessage) {
        if (!WechatEventType.SUBSCRIBE.equals(wechatMessage.getEventType()) &&
                !me.equals(wechatMessage.getFromUserName()) &&
                !myGirl.equals(wechatMessage.getFromUserName())){
            System.out.println("reject");
            return rejectOthers(wechatMessage);
        }

        String messageType = wechatMessage.getMessageType();

        if (WechatMessageType.EVENT.equals(messageType)) {
            return processEventMessage(wechatMessage);
        } else {

        }
        return null;
    }

    private WechatMessage processEventMessage(WechatMessage wechatMessage) {
        String eventType = wechatMessage.getEventType();

        switch (eventType) {
            case WechatEventType.SUBSCRIBE:
                WechatMessage wechatTextMessage = new WechatMessage();
                wechatTextMessage.setFromUserName(wechatMessage.getToUserName());
                wechatTextMessage.setToUserName(wechatMessage.getFromUserName());
                wechatTextMessage.setMessageType(WechatMessageType.TEXT);
                wechatTextMessage.setCreateTime(new Date().getTime());

                if (wechatMessage.getFromUserName().equals(me) || wechatMessage.getFromUserName().equals(myGirl)) {
                    wechatTextMessage.setContent("???????????????, ???????????????????????????");
                } else {
                    wechatTextMessage.setContent("??????, ????????????????????????????????????????????????(os: ?????????????????????), ???????????????????????? \n\n???????????????????????????/?????????????????????????????????, ???????????????GitHub?????????????????????????????????????????????????????????????????????????????????????????????\n\n??????????????????^-^ \n\nGitHub: https://github.com/WeiyuSun/wechat-push");
                }

                return wechatTextMessage;
        }

        return null;
    }

    private WechatMessage rejectOthers(WechatMessage fromWechat){
        WechatMessage wechatMessage = new WechatMessage();
        wechatMessage.setFromUserName(fromWechat.getToUserName());
        wechatMessage.setToUserName(fromWechat.getFromUserName());
        wechatMessage.setMessageType(WechatMessageType.TEXT);
        wechatMessage.setCreateTime(new Date().getTime());
        wechatMessage.setContent("??????????????????????????????...\uD83E\uDD24");
        return wechatMessage;
    }
}
