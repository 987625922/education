package com.project.gelingeducation.common.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * 自定义全局异常类
 */
public class AllException extends RuntimeException {

    /**
     * 状态码
     */
    private Integer code;

    private Object data = null;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    /**
     * 异常消息
     */
    private String message;

    public AllException(int code, String msg) {
        super(msg);
        this.code = code;
        this.message = msg;
        timestamp = LocalDateTime.now();
    }

    public AllException(StatusEnum statusEnum) {
        super(statusEnum.getMessage());
        this.code = statusEnum.getCode();
        this.message = statusEnum.getMessage();
        timestamp = LocalDateTime.now();
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
