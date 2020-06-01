package com.project.gelingeducation.common.aspect;

import com.google.gson.JsonObject;
import com.project.gelingeducation.common.annotation.Cache;
import com.project.gelingeducation.common.redis.servicr.RedisService;
import com.project.gelingeducation.common.utils.JsonUtils;
import org.aspectj.lang.JoinPoint;
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
import java.time.LocalDate;

@Component
@Aspect
public class RedisAspect {

    @Autowired
    RedisService redisService;

    /**设置切入点*/
    //方法上面有@NeedCacheAop的方法,增加灵活性
    @Pointcut("@annotation(com.project.gelingeducation.common.annotation.Cache)")
    public void annotationAspect(){}

    /**环绕通知*/
    @Around(value = "annotationAspect()")
    public Object doAround(ProceedingJoinPoint joinPoint){
        //获取请求
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        //存储接口返回值
        Object object = new Object();

        //获取注解对应配置过期时间
        Cache cacheAop = ((MethodSignature)joinPoint.getSignature()).getMethod().getAnnotation(Cache.class);  //获取注解自身
        long time;
        if (cacheAop == null){//规避使用第二种切点进行缓存操作的情况
            time = 30*60;  //默认过期时间为30分钟
        }else{
            time = cacheAop.time();
        }
        //获取key
        String key = redisService.getKeyForAop(joinPoint,request);
        if (redisService.containKey(key)){
            String obj = redisService.get(key);
            if ("fail".endsWith(obj)){  //规避redis服务不可用
                try {
                    //执行接口调用的方法
                    joinPoint.proceed();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }else{
                JsonObject klass = JsonUtils.unserializeObject(obj);
                return new ResponseEntity<>(klass.get("body"), HttpStatus.OK) ;
            }
        }else{
            try {
                ////执行接口调用的方法并获取返回值
                object = joinPoint.proceed();
                String serobj = JsonUtils.jsonToString(object);
                redisService.set(key,serobj,time);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        return object;
    }
}

}
