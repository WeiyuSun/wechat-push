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
}
