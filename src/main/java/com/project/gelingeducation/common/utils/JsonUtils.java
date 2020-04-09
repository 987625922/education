package com.project.gelingeducation.common.utils;


import com.alibaba.fastjson.JSON;

import java.util.List;

public class JsonUtils {

    /**
     * 转成json
     *
     * @param object 对象
     * @return 字符串
     */
    public static String jsonString(Object object) {
        return JSON.toJSONString(object);
    }

    /**
     * 转成bean
     *
     * @param jsonString 字符串
     * @param cls        类
     * @return T
     */
    public static <T> T jsonToBean(String jsonString, Class<T> cls) {
        return JSON.parseObject(jsonString, cls);
    }

    /**
     * 转成list
     * 解决泛型问题
     *
     * @param json json
     * @param cls  类
     * @param <T>  T
     * @return T列表
     */
    public static <T> List<T> jsonToList(String json, Class<T> cls) {
        List<T> list = JSON.parseArray(json, cls);
        return list;
    }


}
