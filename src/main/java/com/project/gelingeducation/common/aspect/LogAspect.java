package com.project.gelingeducation.common.aspect;

import com.project.gelingeducation.common.utils.HttpUtil;
import com.project.gelingeducation.common.utils.ShiroUtil;
import com.project.gelingeducation.common.utils.SpringUtil;
import com.project.gelingeducation.common.utils.ThrowableUtil;
import com.project.gelingeducation.entity.Log;
import com.project.gelingeducation.service.ILogService;
import lombok.extern.slf4j.Slf4j;
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
 * @author LL
 * @Description：接口错误日志切面
 */
@Component
@Aspect
@Slf4j
public class LogAspect {

    /**
     * log实体类的service
     */
    private final ILogService logService;

    /**
     * 保存时间戳
     */
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
     * 配置环绕通知,使用在方法logPointcut()上注册的切入点，
     * 用于保存接口请求信息
     */
    @Around("logPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        currentTime.set(System.currentTimeMillis());
        //调用方法
        Object result = joinPoint.proceed();
        //日志实体
        Log log = new Log("INFO", System.currentTimeMillis() - currentTime.get());
        //删除时间戳的保存
        currentTime.remove();
        //获取servlet
        HttpServletRequest request = SpringUtil.getHttpServletRequest();
        //获取MethodSignature，包括反射需要的Method和Class
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取Method
        Method method = signature.getMethod();
        //获取方法上的切点
        com.project.gelingeducation.common.annotation.Log aopLog
                = method.getAnnotation(com.project.gelingeducation.common.annotation.Log.class);
        //获取方法名
        String methodName = joinPoint.getTarget().getClass().getName() + "." + signature.getName() + "()";
        //获取方法参数的名字和值
        StringBuilder params = new StringBuilder("{");
        Object[] argValues = joinPoint.getArgs();
        String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        if (argValues != null) {
            for (int i = 0; i < argValues.length; i++) {
                params.append(" ").append(argNames[i]).append(": ").append(argValues[i]);
            }
        }
        String userName = "";
        try {
            userName = ShiroUtil.getUserName();
        } catch (Exception e) {

        }
        //保存log
        log.setUsername(userName).setBrowser(HttpUtil.getBrowser(request))
                .setRequestIp(HttpUtil.getIp(request))
                .setDescription(aopLog.value()).setMethod(methodName)
                .setParams(params.toString() + "}").setAddress(HttpUtil.getCityInfo(log.getRequestIp()))
                .setExceptionDetail("").setIsSolve(0);
        //log存入数据库
        logService.save(log);
        return result;
    }

    /**
     * 配置异常通知
     * 切点报错后调用
     */
    @AfterThrowing(pointcut = "logPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        //初始化日志实体
        Log log = new Log("ERROR", System.currentTimeMillis()
                - currentTime.get());
        //删除时间戳
        currentTime.remove();
        //获取servlet
        HttpServletRequest request = SpringUtil.getHttpServletRequest();
        //获取MethodSignature，包括反射需要的Method和Class
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取Method
        Method method = signature.getMethod();
        //获取方法上的切点
        com.project.gelingeducation.common.annotation.Log aopLog
                = method.getAnnotation(com.project.gelingeducation.common.annotation.Log.class);
        //获取方法名
        String methodName = joinPoint.getTarget().getClass().getName() + "." + signature.getName() + "()";
        //获取方法参数的名字和值
        StringBuilder params = new StringBuilder("{");
        Object[] argValues = joinPoint.getArgs();
        String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        if (argValues != null) {
            for (int i = 0; i < argValues.length; i++) {
                params.append(" ").append(argNames[i]).append(": ").append(argValues[i]);
            }
        }
        //保存log
        log.setUsername(ShiroUtil.getUserName()).setBrowser(HttpUtil.getBrowser(request)).setRequestIp(HttpUtil.getIp(request))
                .setDescription(aopLog.value()).setMethod(methodName)
                .setParams(params.toString() + "}").setAddress(HttpUtil.getCityInfo(log.getRequestIp()))
                .setExceptionDetail(ThrowableUtil.getStackTrace(e).substring(0, 3000)).setIsSolve(0);
        //log存入数据库
        logService.save(log);
    }
}
