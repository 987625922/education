package com.project.gelingeducation.common.exception;

/**
 * 自定义全局异常类
 */
public class AllException extends RuntimeException {

    /**
     * 状态码
     */
    private Integer code;

    private Object data = null;

    private long time;
    /**
     * 异常消息
     */
    private String message;

    public AllException(int code, String msg) {
        super(msg);
        this.code = code;
        this.message = msg;
        this.time = System.currentTimeMillis();
    }

    public AllException(StatusEnum statusEnum) {
        super(statusEnum.getMessage());
        this.code = statusEnum.getCode();
        this.message = statusEnum.getMessage();
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
