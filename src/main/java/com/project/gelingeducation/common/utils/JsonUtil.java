package com.project.gelingeducation.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @Author: LL
 * @Description: json处理的工具类
 */
public class JsonUtil {

    private static class JacksonHelper {
        private static ObjectMapper instance = new ObjectMapper();
    }

    /**
     * 单例模式
     *
     * @return 返回单例的jackson
     */
    private static ObjectMapper getInstance() {
        return JacksonHelper.instance;
    }

    /**
     * 转成json
     *
     * @param object 对象
     * @return 字符串
     */
    public static String jsonToString(Object object) throws JsonProcessingException {
        String gsonString = getInstance().writeValueAsString(object);
        return gsonString;
    }

    /**
     * 转成bean
     *
     * @param jsonString 字符串
     * @param cls        类
     * @return T
     */
    public static <T> T jsonToBean(String jsonString, Class<T> cls) throws IOException {
        T t = getInstance().readValue(jsonString, cls);
        return t;
    }
}
