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
 * 接口错误日志切面
 *
 * @author 98762
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
     */
    @Around("logPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        currentTime.set(System.currentTimeMillis());
        Object result = joinPoint.proceed();
        Log log = new Log("INFO", System.currentTimeMillis() - currentTime.get());
        currentTime.remove();
        HttpServletRequest request = SpringContextUtils.getHttpServletRequest();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        com.project.gelingeducation.common.annotation.Log aopLog
                = method.getAnnotation(com.project.gelingeducation.common.annotation.Log.class);
        String methodName = joinPoint.getTarget().getClass().getName() + "." + signature.getName() + "()";
        StringBuilder params = new StringBuilder("{");
        Object[] argValues = joinPoint.getArgs();
        String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        if (argValues != null) {
            for (int i = 0; i < argValues.length; i++) {
                params.append(" ").append(argNames[i]).append(": ").append(argValues[i]);
            }
        }

        log.setUsername(getUsername()).setBrowser(HttpUtil.getBrowser(request)).setRequestIp(HttpUtil.getIp(request))
                .setDescription(aopLog.value()).setMethod(methodName)
                .setParams(params.toString() + "}").setAddress(HttpUtil.getCityInfo(log.getRequestIp())).setExceptionDetail("")
                .setIsSolve(0);
        logService.save(log);
        return result;
    }

    /**
     * 配置异常通知
     */
    @AfterThrowing(pointcut = "logPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        Log log = new Log("ERROR", System.currentTimeMillis()
                - currentTime.get());
        currentTime.remove();
        HttpServletRequest request = SpringContextUtils.getHttpServletRequest();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        com.project.gelingeducation.common.annotation.Log aopLog
                = method.getAnnotation(com.project.gelingeducation.common.annotation.Log.class);
        String methodName = joinPoint.getTarget().getClass().getName() + "." + signature.getName() + "()";
        StringBuilder params = new StringBuilder("{");
        Object[] argValues = joinPoint.getArgs();
        String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        if (argValues != null) {
            for (int i = 0; i < argValues.length; i++) {
                params.append(" ").append(argNames[i]).append(": ").append(argValues[i]);
            }
        }
        log.setUsername(getUsername()).setBrowser(HttpUtil.getBrowser(request)).setRequestIp(HttpUtil.getIp(request))
                .setDescription(aopLog.value()).setMethod(methodName)
                .setParams(params.toString() + "}").setAddress(HttpUtil.getCityInfo(log.getRequestIp()))
                .setExceptionDetail(ThrowableUtil.getStackTrace(e).substring(0, 3000)).setIsSolve(0);
        logService.save(log);
    }

    /**
     * 通过shiro返回用户名
     *
     * @return /
     */
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
