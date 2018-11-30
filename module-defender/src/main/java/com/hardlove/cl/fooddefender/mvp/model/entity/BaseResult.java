package com.hardlove.cl.fooddefender.mvp.model.entity;

/**
 * Created by CL on 2017/8/25.
 * 描述：封装服务器返回的公有数据
 */

public class BaseResult<T>{

    private int code;
    private T data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
