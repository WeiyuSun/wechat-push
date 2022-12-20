package com.weiyuscode.wechatpush.service;

public interface DailyMessageService {
    void renewMessage(String todayMessage);
    void renewHint(String hint);
    String getTodayMessage();
    String getTodayHint();
}
