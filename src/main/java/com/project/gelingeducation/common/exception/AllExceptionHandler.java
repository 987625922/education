package com.project.gelingeducation.common.exception;

import com.project.gelingeducation.common.dto.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理控制器
 * @author LL
 */
@Slf4j
@ControllerAdvice
public class AllExceptionHandler {

    @ExceptionHandler(value = Exception.class) //捕获一个全局的异常
    @ResponseBody //在异常出现时好把bean转换成json返回给前端
    public JsonResult handler(Exception e) {
        log.error("【异常信息】", e);
        if (e instanceof AllException) {
            AllException allException = (AllException) e;
            return JsonResult.buildError(allException.getMessage(),
                    allException.getCode());
        } else if (e instanceof UnauthorizedException) {
            return JsonResult.buildStatus(StatusEnum.USER_NO_PERMISSION);
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            return JsonResult.buildStatus(StatusEnum.REQUEST_METHOD_NOT_SUPPORT);
        } else if (e instanceof HttpMessageNotReadableException) {
            return JsonResult.buildStatus(StatusEnum.HTTP_BODY_MISS);
        } else if (e instanceof MissingServletRequestParameterException) {
            return JsonResult.buildStatus(StatusEnum.MISS_PARAME_EXCEPTION);
        } else if (e instanceof RedisConnectionFailureException) {
            return JsonResult.buildStatus(StatusEnum.REDIS_CONNECTTION_FAILUER_EXCEPTION);
        } else {
            return JsonResult.buildError(StatusEnum.UNKNOWD_ERROR.getMessage(),
                    StatusEnum.UNKNOWD_ERROR.getCode());
        }
    }

}
