/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.project.gelingeducation.common.aspect;

import com.project.gelingeducation.common.utils.HttpUtil;
import com.project.gelingeducation.common.utils.ThrowableUtil;
import com.project.gelingeducation.controller.SpringContextUtils;
import com.project.gelingeducation.entity.Log;
import com.project.gelingeducation.entity.User;
import com.project.gelingeducation.service.ILogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 接口日志切面
 */
@Component
@Aspect
@Slf4j
public class LogAspect {

    private final ILogService logService;

    ThreadLocal<Long> currentTime = new ThreadLocal<>();

    public LogAspect(ILogService logService) {
        this.logService = logService;
    }

    /**
     * 配置切入点
     * 该方法无方法体,主要为了让同类中其他方法使用此切入点
     */
    @Pointcut("@annotation(com.project.gelingeducation.common.annotation.Log)")
    public void logPointcut() {
    }

    /**
     * 配置环绕通知,使用在方法logPointcut()上注册的切入点
     *
     * @param joinPoint join point for advice
     */
    @Around("logPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result;
        currentTime.set(System.currentTimeMillis());
        result = joinPoint.proceed();
        Log log = new Log("INFO", System.currentTimeMillis() - currentTime.get());
        HttpServletRequest request = SpringContextUtils.getHttpServletRequest();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        com.project.gelingeducation.common.annotation.Log aopLog
                = method.getAnnotation(com.project.gelingeducation.common.annotation.Log.class);
        StringBuilder params = new StringBuilder("{");
        Object[] argValues = joinPoint.getArgs();
        //参数名称
        String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        if (argValues != null) {
            for (int i = 0; i < argValues.length; i++) {
                params.append(" ").append(argNames[i]).append(": ").append(argValues[i]);
            }
        }
        logService.save(getUsername(), HttpUtil.getBrowser(request), HttpUtil.getIp(request),
                aopLog.value(), joinPoint.getTarget().getClass().getName() + "." + signature.getName() + "()"
                , joinPoint.getArgs(), argNames, params.toString(), log);
        currentTime.remove();
        return result;
    }


    /**
     * 配置异常通知
     *
     * @param joinPoint join point for advice
     * @param e         exception
     */
    @AfterThrowing(pointcut = "logPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        Log log = new Log("ERROR", System.currentTimeMillis()
                - currentTime.get());
        currentTime.remove();
        log.setExceptionDetail(ThrowableUtil.getStackTrace(e).substring(0, 3000));
        HttpServletRequest request = SpringContextUtils.getHttpServletRequest();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        com.project.gelingeducation.common.annotation.Log aopLog
                = method.getAnnotation(com.project.gelingeducation.common.annotation.Log.class);
        StringBuilder params = new StringBuilder("{");
        Object[] argValues = joinPoint.getArgs();
        //参数名称
        String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        if (argValues != null) {
            for (int i = 0; i < argValues.length; i++) {
                params.append(" ").append(argNames[i]).append(": ").append(argValues[i]);
            }
        }
        logService.save(getUsername(), HttpUtil.getBrowser(request), HttpUtil.getIp(request),
                aopLog.value(), joinPoint.getTarget().getClass().getName() + "." + signature.getName() + "()"
                , joinPoint.getArgs(), argNames, params.toString(), log);
    }

    public String getUsername() {
        User user;
        try {
            user = (User) SecurityUtils.getSubject().getPrincipal();
        } catch (Exception e) {
            return "";
        }

        if (user == null) {
            return "";
        } else {
            return user.getUserName();
        }

    }
}
