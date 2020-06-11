package com.project.gelingeducation.common.config;

public class GLConstant {
    //user缓存前缀
    public static final String TOKEN_CACHE_PREFIX = "gl.cache.token.";
    public static final Integer TOKEN_CACHE_TIME = 1;
    /**
     * 常用接口
     */
    public static class Url{
        // IP归属地查询
        public static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp?ip=%s&json=true";
    }

}
