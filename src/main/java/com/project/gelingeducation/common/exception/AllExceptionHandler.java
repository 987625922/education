package com.project.gelingeducation.common.exception;

import com.project.gelingeducation.common.dto.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理控制器
 */
@Slf4j
@ControllerAdvice
public class AllExceptionHandler {

    @ExceptionHandler(value = Exception.class) //捕获一个全局的异常
    @ResponseBody //在异常出现时好把bean转换成json返回给前端
    public JsonData Handler(Exception e) {
        log.error("【异常信息】", e);
        if (e instanceof AllException) {
            AllException allException = (AllException) e;
            return JsonData.buildError(allException.getMessage(),
                    allException.getCode());
        } else if (e instanceof UnauthorizedException) {
            return JsonData.buildStatus(StatusEnum.USER_NO_PERMISSION);
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            return JsonData.buildStatus(StatusEnum.REQUEST_METHOD_NOT_SUPPORT);
        } else if (e instanceof HttpMessageNotReadableException) {
            return JsonData.buildStatus(StatusEnum.HTTP_BODY_MISS);
        } else if (e instanceof MissingServletRequestParameterException){
            return JsonData.buildStatus(StatusEnum.MISS_PARAME_EXCEPTION);
        }else {
            return JsonData.buildError(StatusEnum.UNKNOWD_ERROR.getMessage(),
                    StatusEnum.UNKNOWD_ERROR.getCode());
        }
    }

}
