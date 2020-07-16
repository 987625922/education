package com.project.gelingeducation.common.utils;

import com.project.gelingeducation.common.exception.AllException;
import com.project.gelingeducation.common.exception.StatusEnum;
import org.hibernate.exception.ConstraintViolationException;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @Author: LL
 * @Description: 错误工具类
 */
public class ThrowableUtil {

    /**
     * 获取堆栈信息
     */
    public static String getStackTrace(Throwable throwable){
        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)) {
            throwable.printStackTrace(pw);
            return sw.toString();
        }
    }


    public static void throwForeignKeyException(Throwable e){
        Throwable t = e.getCause();
        while ((t != null) && !(t instanceof ConstraintViolationException)) {
            t = t.getCause();
        }
        if (t != null) {
            throw new AllException(StatusEnum.GET_EXCEPTION_NULL);
        }
        assert false;
        throw new AllException(StatusEnum.DEL_EXCEPTION_FAILE);
    }
}
