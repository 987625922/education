package com.project.gelingeducation.common.exception;

public enum AllExceptionEnum {
    UNKNOWD_ERROR(new AllException(-3001,"全局异常，未知错误")),
    NO_USER(new AllException(-3101,"用户未注册"))
    ,ACCOUNT_PASSWORD_ERROR(new AllException(-3102, "账号密码错误"))
    ,NO_LOGIN(new AllException(-3103,"用户未登录"))
    ,BAN_USER(new AllException(-3104, "账号锁定"))
    ,ACCOUNT_ALREADY_EXISTS(new AllException(-3105, "账号已存在"))
    ,USER_NO_PERMISSION(new AllException(-3106,"账号无权限"))
    ,ADD_USER_NO_ROLE(new AllException(-3107,"添加的用户没有身份"))
    ,REQUEST_METHOD_NOT_SUPPORT(new AllException(-3401,"请求的方式不支持"));

    private AllException allException;

    AllExceptionEnum(AllException allException) {
        this.allException = allException;
    }

    public AllException getAllException() {
        return allException;
    }

    public String getMsg(){
        return allException.getMsg();
    }

    public int getCode(){
        return allException.getCode();
    }
}
