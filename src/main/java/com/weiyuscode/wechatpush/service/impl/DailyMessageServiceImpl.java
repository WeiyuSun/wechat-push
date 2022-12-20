package com.weiyuscode.wechatpush.service.impl;
import com.weiyuscode.wechatpush.service.DailyMessageService;
import org.springframework.stereotype.Service;

@Service
public class DailyMessageServiceImpl implements DailyMessageService {
    private String todayMessage = "";
    private String todayHint = "";
    public void renewMessage(String todayMessage){
        this.todayMessage = todayMessage;
        System.out.println(this.todayMessage);
    }

    @Override
    public void renewHint(String hint) {
        this.todayHint = hint;
        System.out.println(this.todayHint);
    }

    public String getTodayMessage(){
        return todayMessage;
    }

    @Override
    public String getTodayHint() {
        return todayHint;
    }
}
