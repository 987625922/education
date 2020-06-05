package com.project.gelingeducation.common.dto;

import com.project.gelingeducation.common.exception.StatusEnum;

import java.io.Serializable;

/**
 * 功能描述：工具类
 */
public class JsonData implements Serializable {


    private static final long serialVersionUID = 1L;

    private Integer code; // 状态码 200 表示成功，1表示处理中，-1表示失败
    private Object data; // 数据
    private String msg;// 描述
    private Long time;

    public JsonData(Integer code, Object data, String msg, Long time) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.time = time;
    }

    // 成功，传入数据
    public static JsonData buildSuccess() {
        return new JsonData(StatusEnum.OK.getCode(), null, StatusEnum.OK.getMessage(), System.currentTimeMillis());
    }

    // 成功，传入数据
    public static JsonData buildSuccess(Object data) {
        return new JsonData(StatusEnum.OK.getCode(), data, StatusEnum.OK.getMessage(), System.currentTimeMillis());
    }

    // 成功，传入数据,及描述信息
    public static JsonData buildSuccess(Object data, String msg) {
        return new JsonData(StatusEnum.OK.getCode(), data, msg, System.currentTimeMillis());
    }

    // 成功，传入数据,及状态码
    public static JsonData buildSuccess(Object data, int code) {
        return new JsonData(code, data, null, System.currentTimeMillis());
    }

    // 失败，传入描述信息,状态码
    public static JsonData buildError(String msg, Integer code) {
        return new JsonData(code, null, msg, System.currentTimeMillis());
    }

    public static JsonData buildStatus(StatusEnum statusEnum) {
        return new JsonData(statusEnum.getCode(), null, statusEnum.getMessage(), System.currentTimeMillis());
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "JsonData [code=" + code + ", data=" + data + ", msg=" + msg
                + ",time=" + time + "]";
    }

}
