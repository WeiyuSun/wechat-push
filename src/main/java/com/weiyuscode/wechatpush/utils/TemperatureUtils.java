package com.weiyuscode.wechatpush.utils;

import com.weiyuscode.wechatpush.pojo.TemplateMsgDataContent;
import com.weiyuscode.wechatpush.pojo.Weather;

public class TemperatureUtils {

    public static TemplateMsgDataContent generateTemperatureContent(Integer temperature, boolean addEmoji){
        String content = "" + temperature + "°C";
        String textColor = TextColorUtils.black;
        return getTemplateMsgDataContent(addEmoji, content, temperature);
    }

    public static TemplateMsgDataContent generateFeelTemperatureContent(Weather weatherInfo, boolean addEmoji){
        String content =  weatherInfo.getTempFeel() + "°C";

        Integer tempFeel = weatherInfo.getTempFeel();
        Integer tempMax = weatherInfo.getTempMax();
        Integer tempMin = weatherInfo.getTempMin();

        if (tempFeel - tempMax >= 5 || tempMin - tempFeel >= 5) {
            content += "别被骗到>_<!";
        }

        return getTemplateMsgDataContent(addEmoji, content, tempFeel);
    }

    private static TemplateMsgDataContent getTemplateMsgDataContent(boolean addEmoji, String content, Integer tempFeel) {
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
