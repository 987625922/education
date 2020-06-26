package com.project.gelingeducation.common.config;

public class GLConstant {
    //user的redis缓存前缀
    public static final String TOKEN_CACHE_PREFIX = "gl.cache.token.";
    public static final String TOKEN_SIGN ="X-Access-Token";
    //token过期时间
    public static final Long TOKEN_CACHE_TIME_SECONDS = 60*60*12L;
    //
    public static final String CODE_PREFIX = "gl_captcha_";

}
