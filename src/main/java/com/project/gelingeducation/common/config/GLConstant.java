package com.project.gelingeducation.common.config;

/**
 * 项目的配置信息
 */
public class GLConstant {
    /**
     * user的redis缓存前缀
     */
    public static final String TOKEN_CACHE_PREFIX = "gl.cache.token.";
    /**
     * 前端传递tonken的key的值
     */
    public static final String TOKEN_SIGN = "X-Access-Token";
    /**
     * token过期时间
     */
    public static final Long TOKEN_CACHE_TIME_SECONDS = 60 * 60 * 12L;
    /**
     * 验证码保存redis前置字符串
     */
    public static final String CODE_PREFIX = "gl.captcha.";
    /**
     * 保存在redis的总共登录次数的key
     */
    public static final String ALL_LOGIN_NUM_KEY = "gl.data.allloginnum";
    /**
     * 保存在redis的今日登录次数的key
     */
    public static final String TODAY_LOGIN_NUM_KEY = "gl.data.todayloginnum";
    /**
     * 保存在redis的今日登录ip数
     */
    public static final String TODAY_LOGIN_IP_NUM_KEY = "gl.data.todayloginipnum";
}
