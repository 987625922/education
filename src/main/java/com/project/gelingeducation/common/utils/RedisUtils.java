package com.project.gelingeducation.common.utils;

import org.aspectj.lang.JoinPoint;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

public class RedisUtils {

    public static String getKeyForAop(JoinPoint joinPoint,
                               HttpServletRequest request) {
        //获取参数的序列化
        Object[] objects = joinPoint.getArgs();
        String args = JsonUtils.jsonToString(objects[0]);
        //获取请求url
        String url = request.getRequestURI();
        //获取请求的方法
        String method = request.getMethod();
        //获取当前日期,规避默认init情况
        String date = LocalDate.now().toString();
        //key值获取
        return args + url + method + date;
    }

}
