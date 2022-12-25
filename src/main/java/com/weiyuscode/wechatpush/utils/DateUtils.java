package com.weiyuscode.wechatpush.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private final static SimpleDateFormat ymdDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static Long daysBetween(Date day1, Date day2) {
        return (day2.getTime() - day1.getTime()) / (1000 * 3600 * 24);
    }

    public static SimpleDateFormat getYmdDateFormat() {
        return ymdDateFormat;
    }

    public static String getWeekdayContent(int weekday) {
        return switch (weekday) {
            case 2 -> "周一 (ノへ`、)";
            case 3 -> "周二 ￣△￣";
            case 4 -> "周三 (´v｀)2 days left~";
            case 5 -> "周四 <(*￣▽￣*)/";
            case 6 -> "周五 ٩(ˊᗜˋ*)و";
            case 7 -> "周六";
            case 1 -> "周天";
            default -> "";
        };
    }
}
