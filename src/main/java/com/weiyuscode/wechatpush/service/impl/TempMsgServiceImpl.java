package com.weiyuscode.wechatpush.service.impl;
import com.alibaba.fastjson.JSONObject;
import com.weiyuscode.wechatpush.entity.TempMsgData;
import com.weiyuscode.wechatpush.entity.TemplateMsg;
import com.weiyuscode.wechatpush.service.TempMsgService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TempMsgServiceImpl implements TempMsgService {

    @Value("${wechat.config.openID}")
    private String touser;

    @Value("${wechat.config.tempID}")
    private String tempID;
    @Override
    public TemplateMsg getTempMsg() {
        TemplateMsg msg = new TemplateMsg();
        TempMsgData dataDetail = new TempMsgData();
        dataDetail.setDate("12/26/2022");
        dataDetail.setRelationLen("6");
        dataDetail.setDailyMsg("test");
        dataDetail.setTemperature("-1");
        dataDetail.setWeekDay("5");
        dataDetail.setWeekDayHint("hint");
        msg.setData(dataDetail);

        msg.setTouser(touser);
        msg.setTemplateID(tempID);
        return msg;
    }
}
