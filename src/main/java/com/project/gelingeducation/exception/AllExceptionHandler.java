package com.project.gelingeducation.exception;

import com.project.gelingeducation.domain.JsonData;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理控制器
 */
@ControllerAdvice
public class AllExceptionHandler {

    //@ExceptionHandler(value = Exception.class) 捕获一个全局的异常
    @ExceptionHandler(value = Exception.class)
    @ResponseBody //在异常出现时好把bean转换成json返回给前端
    public JsonData Handler(Exception e) {
        if (e instanceof AllException) {
            AllException allException = (AllException) e;
            return JsonData.buildError(allException.getMsg(), allException.getCode());
        } else {
            return JsonData.buildError("全局异常，未知错误");
        }
    }

}
