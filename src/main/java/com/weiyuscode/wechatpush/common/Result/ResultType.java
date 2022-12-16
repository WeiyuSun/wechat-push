package com.weiyuscode.wechatpush.common.Result;

public enum ResultType {
    SUCCESS(200, "success"),
    ERROR(400, "ERROR");

    private final Integer code;
    private final String msg;
    ResultType(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode(){
        return code;
    }

    public String getMsg(){
        return msg;
    }
}
