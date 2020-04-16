package com.project.gelingeducation.common.exception;

public enum AllExceptionEnum {
    NO_USER(new AllException(-101,"用户未注册"))
    ,ACCOUNT_PASSWORD_ERROR(new AllException(-102, "账号密码错误"))
    ,NO_LOGIN(new AllException(-103,"用户未登录"))
    ,BAN_USER(new AllException(-104, "账号锁定"))
    ,ACCOUNT_ALREADY_EXISTS(new AllException(-105, "账号已存在"))
    ,USER_NO_PERMISSION(new AllException(-106,"账号无权限"))
    ,ADD_USER_NO_ROLE(new AllException(-107,"添加的用户没有身份"));

    private AllException allException;

    AllExceptionEnum(AllException allException) {
        this.allException = allException;
    }

    public AllException getAllException() {
        return allException;
    }
}
