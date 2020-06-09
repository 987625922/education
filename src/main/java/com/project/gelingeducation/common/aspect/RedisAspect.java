package com.project.gelingeducation.common.aspect;

import com.project.gelingeducation.common.annotation.Cache;
import com.project.gelingeducation.common.redis.JedisCacheClient;
import com.project.gelingeducation.common.utils.JsonUtils;
import com.project.gelingeducation.common.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
@Aspect
public class RedisAspect {

    @Autowired
    JedisCacheClient redisService;

    /**
     * 设置切入点
     */
    //方法上面有@NeedCacheAop的方法,增加灵活性
    @Pointcut("@annotation(com.project.gelingeducation.common.annotation.Cache)")
    public void annotationAspect() {
    }

    /**
     * 环绕通知
     */
    @Around(value = "annotationAspect()")
    public Object doAround(ProceedingJoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        Object object = new Object();

        //获取注解对应配置过期时间
        Cache cacheAop = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(Cache.class);  //获取注解自身
        int time;
        if (cacheAop == null) {
            time = 30 * 60;  //默认过期时间为30分钟
        } else {
            time = cacheAop.time();
        }

        String key = RedisUtils.getKeyForAop(joinPoint, request);
        if (redisService.containKey(key)) {
            String obj = redisService.get(key);
            if ("fail".endsWith(obj)) {  //规避redis服务不可用
                try {
                    joinPoint.proceed();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            } else {
//                JsonObject klass = JsonUtils.unserializeObject(obj);
//                return new ResponseEntity<>(klass.get("body"), HttpStatus.OK) ;
                return new ResponseEntity<>(obj, HttpStatus.OK);
            }
        } else {
            try {
                //执行接口调用的方法并获取返回值
                object = joinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            String serobj = JsonUtils.jsonToString(object);
            redisService.set(key, serobj, time);
        }
        return object;
    }
}


