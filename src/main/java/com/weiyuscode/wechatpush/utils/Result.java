package com.weiyuscode.wechatpush.utils;

public class Result<T> {
    private Integer code;
    private String msg;
    private T data;

    public Result(){}

    public Result(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public Result(Integer code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Result<T> OK(){
        return new Result<>(ResultType.SUCCESS.getCode(), ResultType.SUCCESS.getMsg());
    }

    public static <T> Result<T> error(Integer code, String msg){
        return new Result<>(code, msg);
    }

    public static <T> Result<T> OK(T data){
        return new Result<>(ResultType.SUCCESS.getCode(), ResultType.SUCCESS.getMsg(), data);
    }

    public T getData(){
        return data;
    }

    public Integer getCode(){
        return code;
    }

    public String getMsg(){
        return msg;
    }
}
